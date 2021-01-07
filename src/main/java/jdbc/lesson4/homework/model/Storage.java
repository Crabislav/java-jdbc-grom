package jdbc.lesson4.homework.model;

public class Storage {
    private long id;
    private String formatsSupported;
    private String storageCountry;
    private long storageMaxSize;

    public Storage(long id, String formatsSupported, String storageCountry, long storageMaxSize) {
        this.id = id;
        this.formatsSupported = formatsSupported;
        this.storageCountry = storageCountry;
        this.storageMaxSize = storageMaxSize;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFormatsSupported() {
        return formatsSupported;
    }

    public void setFormatsSupported(String formatsSupported) {
        this.formatsSupported = formatsSupported;
    }

    public String getStorageCountry() {
        return storageCountry;
    }

    public void setStorageCountry(String storageCountry) {
        this.storageCountry = storageCountry;
    }

    public long getStorageMaxSize() {
        return storageMaxSize;
    }

    public void setStorageMaxSize(long storageMaxSize) {
        this.storageMaxSize = storageMaxSize;
    }

    @Override
    public String toString() {
        return "Storage{" +
                "id=" + id +
                ", formatsSupported='" + formatsSupported + '\'' +
                ", storageCountry='" + storageCountry + '\'' +
                ", storageMaxSize=" + storageMaxSize +
                '}';
    }
}
