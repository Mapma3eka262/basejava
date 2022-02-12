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
        } else if (checkResume(r)) System.out.println("Резюме с uuid " + r + " создано");
        else {
            storage[numberOfResume] = r;
            numberOfResume++;
        }
    }

    public void update(Resume r) {
        if (r.getUuid() == null) {
            System.out.println("Введите uuid");
        } else if (checkResume(r)) System.out.println("Резюме с uuid" + r + " обновлено");
        else {
            System.out.println("Резюме с uuid " + r + "не создано");
        }
    }

    public Resume get(String uuid) {
        if (checkUuid(uuid) > numberOfResume)
        {
            System.out.println("Резюме с именем " + uuid + " не существует");
            return null;
        } else {
            return storage[checkUuid(uuid)];
        }
    }

    public void delete(String uuid) {
        if (checkUuid(uuid) > numberOfResume)
        {
            System.out.println("Резюме с именем " + uuid + " не существует");
        } else {
            storage[checkUuid(uuid)] = storage[numberOfResume - 1];
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

    private boolean checkResume(Resume r) {
        int compare = 0;
        while (compare < numberOfResume) {
            if (storage[compare].getUuid().equals(r.getUuid())) {
                return true;
            }
            compare++;
        }
        return false;
    }

    private int checkUuid(String uuid) {
        int compare = 0;
        while (compare < numberOfResume) {
            if (storage[compare].getUuid().equals(uuid)) {
                return compare;
            }
            compare++;
        }
        return 10000;
    }
}
