package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int findIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, numberOfResume, searchKey);
    }

    @Override
    protected void deleteResumeFromArray(int index) {
        if ((numberOfResume - index) > 0) {
            System.arraycopy(storage, index + 1, storage, index, numberOfResume - index);
        }
    }

    @Override
    protected void saveResumeToArray(int index, Resume r) {
        System.arraycopy(storage, index, storage, index + 1, numberOfResume - index);
        storage[index] = r;
    }
}