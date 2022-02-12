package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final Resume[] storage = new Resume[10000];
    private int numberOfResume = 0;

    public void clear() {
        Arrays.fill(storage, 0, numberOfResume, null);
        numberOfResume = 0;
    }

    public void save(Resume r) {
        if (r.getUuid() == null) {
            System.out.println("Введите uuid");
        } else if (numberOfResume >= storage.length) {
            System.out.println("Место в хранилище заполнено");
        } else if (findIndex(r.getUuid()) < 0) {
            storage[numberOfResume] = r;
            numberOfResume++;
        } else System.out.println("Резюме с uuid " + r + " создано");
    }

    public void update(Resume r) {
        int indexOfResume = findIndex(r.getUuid());
        if (r.getUuid() == null) {
            System.out.println("Введите uuid");
        } else if (indexOfResume > 0) {
            storage[indexOfResume] = r;
            System.out.println("Резюме с uuid " + r + " обновлено");
        } else {
            System.out.println("Резюме с uuid " + r + "не создано");
        }
    }

    public Resume get(String uuid) {
        if (findIndex(uuid) > numberOfResume) {
            System.out.println("Резюме с именем " + uuid + " не существует");
            return null;
        } else {
            return storage[findIndex(uuid)];
        }
    }

    public void delete(String uuid) {
        int removedIndex = findIndex(uuid);
        if (removedIndex < 0) {
            System.out.println("Резюме с именем " + uuid + " не существует");
        } else {
            storage[removedIndex] = storage[numberOfResume - 1];
            numberOfResume--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, numberOfResume);
    }

    public int size() {
        return numberOfResume;
    }

    private int findIndex(String uuid) {
        for (int i = 0; i < numberOfResume; i++)
        {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
