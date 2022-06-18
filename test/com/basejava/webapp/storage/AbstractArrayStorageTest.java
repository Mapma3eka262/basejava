package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public abstract class AbstractArrayStorageTest {
    private final Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final Resume RESUME_1 = new Resume(UUID_1);

    private static final String UUID_2 = "uuid2";
    private static final Resume RESUME_2 = new Resume(UUID_2);

    private static final String UUID_3 = "uuid3";
    private static final Resume RESUME_3 = new Resume(UUID_3);

    private static final String UUID_4 = "uuid4";
    private static final Resume RESUME_4 = new Resume(UUID_4);


    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void size() {
        AssertStorageSize(3);
    }

    @Test
    public void get() {
        AssertGetResume(RESUME_1);
        AssertGetResume(RESUME_2);
        AssertGetResume(RESUME_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test
    public void clear() {
        storage.clear();
        AssertStorageSize(0);
    }

    @Test
    public void save() {
        storage.save(RESUME_4);
        AssertStorageSize(4);
        AssertGetResume(RESUME_4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(new Resume(UUID_1));
    }

    @Test(expected = StorageException.class)
    public void overflowSave() {
        try {
            for (int i = 4; i <= AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            Assert.fail("Массив заполнен раньше времени");
        }
        storage.save(new Resume());
    }

    @Test
    public void update() {
        Resume updateResume = new Resume(UUID_2);
        storage.update(updateResume);
        Assert.assertSame(updateResume, storage.get(UUID_2));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        Resume updateResume = new Resume("dummy");
        storage.update(updateResume);
    }


    @Test
    public void delete() {
        storage.delete(UUID_1);
        AssertStorageSize(2);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("NotExist");
    }

    @Test
    public void getAll() {
        Resume[] newStorage = storage.getAll();
        Assert.assertEquals(newStorage.length, 3);
        Assert.assertEquals(RESUME_1, newStorage[0]);
        Assert.assertEquals(RESUME_2, newStorage[1]);
        Assert.assertEquals(RESUME_3, newStorage[2]);
    }

    public void AssertStorageSize(int size) {
        Assert.assertEquals(size, storage.size());
    }

    public void AssertGetResume(Resume r) {
        Assert.assertEquals(r, storage.get(r.getUuid()));
    }
}