package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.exception.StorageException;
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
        int index = findIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return storage[index];
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
            throw new StorageException("Место в хранилище заполнено", r.getUuid());
        } else if (index < 0) {
            saveResumeToArray(index, r);
            numberOfResume++;
        } else throw new ExistStorageException(r.getUuid());
    }

    public void update(Resume r) {
        int index = findIndex(r.getUuid());
        if (r.getUuid() == null) {
            System.out.println("Введите uuid");
        } else if (index >= 0) {
            storage[index] = r;
            System.out.println("Резюме с uuid " + r + " обновлено");
        } else {
            throw new NotExistStorageException(r.getUuid());
        }
    }

    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            deleteResumeFromArray(index);
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
