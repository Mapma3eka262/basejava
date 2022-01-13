import java.util.Arrays;
/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int numberOfResume = 0;

    void clear() {
        Arrays.fill(storage, null);
        numberOfResume = 0;
    }

    void save(Resume r) {
        if (r.uuid == null) {
            System.out.println("Введите uuid");
        } else {
            if (numberOfResume==0) {
                storage[numberOfResume] = r;
                numberOfResume++;
            } else {
                if (!checkUuid(r)) System.out.println("Резюме с таким uuid создано");
                else {
                    storage[numberOfResume] = r;
                    numberOfResume++;
                }
            }
        }
    }

    private boolean checkUuid(Resume r) {
        int compare = 0;
        while (compare < numberOfResume)
        {
            if (storage[compare].uuid.equals(r.uuid)) {
                return false;
            }
            compare++;
        }
        return true;
    }

    Resume get(String uuid) {
        Resume search = new Resume();
        for (int i = 0; i < numberOfResume; i++) {
            if (storage[i].uuid.equals(uuid)) {
                search = storage[i];
                break;
            }
        }
        if (search.uuid == null) {
            search.uuid = "Резюме с uuid " + uuid + " не найден";
        }
        return search;
    }

    void delete(String uuid) {
        for (int searchUuid = 0; searchUuid < numberOfResume; searchUuid++) {
            if (storage[searchUuid].uuid.equals(uuid)) {
                storage[searchUuid] = storage[numberOfResume - 1];
                numberOfResume = numberOfResume - 1;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, numberOfResume);
        // return new Resume[0];
    }

    int size() {
        return numberOfResume;
    }
}
