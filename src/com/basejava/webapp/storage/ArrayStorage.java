package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    protected int findIndex(String uuid) {
        for (int i = 0; i < numberOfResume; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void deleteResumeFromArray(int index) {
        storage[index] = storage[numberOfResume - 1];
    }

    @Override
    protected void saveResumeToArray(int index, Resume r) {
        storage[numberOfResume] = r;
    }


}
