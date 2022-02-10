package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int numberOfResume = 0;

    public void clear() {
        Arrays.fill(storage, 0, numberOfResume, null);
        numberOfResume = 0;
    }

    public void save(Resume r) {
        if (r.getUuid() == null) {
            System.out.println("Введите uuid");
        } else if (!checkUuid(r)) System.out.println("Резюме с таким uuid создано");
        else {
            storage[numberOfResume] = r;
            numberOfResume++;
        }
    }

    private boolean checkUuid(Resume r) {
        int compare = 0;
        while (compare < numberOfResume) {
            if (storage[compare].getUuid().equals(r.getUuid())) {
                return false;
            }
            compare++;
        }
        return true;
    }

    public Resume get(String uuid) {
        for (int i = 0; i < numberOfResume; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    public void delete(String uuid) {
        for (int i = 0; i < numberOfResume; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                storage[i] = storage[numberOfResume - 1];
                numberOfResume--;
                break;
            }
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
}
