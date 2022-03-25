package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 3;

    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int numberOfResume = 0;

    public int size() {
        return numberOfResume;
    }

    public Resume get(String uuid) {
        int getIndex = findIndex(uuid);
        if (getIndex < 0) {
            System.out.println("Резюме с uuid " + uuid + " не создано");
            return null;
        }
        return storage[getIndex];
    }

    public void clear() {
        Arrays.fill(storage, 0, numberOfResume, null);
        numberOfResume = 0;
    }

    public void save(Resume r) {
        int index = findIndex(r.getUuid());
        if (r.getUuid() == null) {
            System.out.println("Введите uuid");
        } else if (numberOfResume == STORAGE_LIMIT) {
            System.out.println("Место в хранилище заполнено");
        } else if (index < 0) {
            saveResumeToArray(index, r);
            //storage[numberOfResume] = r;
            numberOfResume++;
        } else System.out.println("Резюме с uuid " + r + " создано");
    }

    public void update(Resume r) {
        int indexOfResume = findIndex(r.getUuid());
        if (r.getUuid() == null) {
            System.out.println("Введите uuid");
        } else if (indexOfResume >= 0) {
            storage[indexOfResume] = r;
            System.out.println("Резюме с uuid " + r + " обновлено");
        } else {
            System.out.println("Резюме с uuid " + r + " не создано");
        }
    }

    public void delete(String uuid) {
        int removedIndex = findIndex(uuid);
        if (removedIndex < 0) {
            System.out.println("Резюме с именем " + uuid + " не существует");
        } else {
            deleteResumeFromArray(removedIndex);
            //storage[removedIndex] = storage[numberOfResume - 1];
            numberOfResume--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, numberOfResume);
    }

    protected abstract int findIndex(String uuid);

    protected abstract void deleteResumeFromArray(int index);

    protected abstract void saveResumeToArray(int index, Resume r);
}
