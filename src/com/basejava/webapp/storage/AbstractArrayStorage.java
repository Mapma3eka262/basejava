package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;

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
        } else {
            return storage[getIndex];
        }
    }

    protected abstract int findIndex(String uuid);
}
