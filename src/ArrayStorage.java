import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int numberOfResume = 0;

    void clear() {
        Arrays.fill(storage,0,numberOfResume,null);
        numberOfResume = 0;
    }

    void save(Resume r) {
        if (r.uuid == null) {
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
            if (storage[compare].uuid.equals(r.uuid)) {
                return false;
            }
            compare++;
        }
        return true;
    }

    Resume get(String uuid) {
        for (int i = 0; i < numberOfResume; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < numberOfResume; i++) {
            if (storage[i].uuid.equals(uuid)) {
                storage[i] = storage[numberOfResume - 1];
                numberOfResume--;
                break;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, numberOfResume);
    }

    int size() {
        return numberOfResume;
    }
}
