package academy.devdojo.chunim.model;

import org.primefaces.model.file.UploadedFile;
import org.primefaces.model.file.UploadedFiles;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import static java.util.Arrays.asList;



public class Car implements Serializable {

    private String name;
    private String type;
    private String brand;
    private String model;
    private String year;
    private String price;
    private Integer id;
    private String description;

    public String imagespath;

    public List<String> imagesFolder = new ArrayList<>();

    public Car() {
    }

    public Car(String name, String type, String brand, String model, String year, String price, Integer id, String description, String imagespath, List<String> imagesFolder) {
        this.name = name;
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.price = price;
        this.id = id;
        this.description = description;
        this.imagespath = imagespath;
        this.imagesFolder = imagesFolder;
    }

    public static String generateFolderName() {
        return UUID.randomUUID().toString();

    }

    public String getImagesPathName() {

        checkFolderContent(imagespath);

        if (imagespath != null) {

            return FacesContext.getCurrentInstance().getExternalContext().getRealPath("resources" +
                    File.separator +
                    "default" + File.separator + "carsFolders" + File.separator + this.imagespath);

        } else {
            throw new RuntimeException("Folder not found");
        }
    }

    public void uploadImagesToFolders(UploadedFiles ups, File file) {

        boolean aux = file.mkdirs();

        for (UploadedFile upf : ups.getFiles()) {

            if (!checkExistentFiles(upf.getFileName(), imagesFolder)) {

                imagesFolder = getImagesListOrIOexception(file, upf);

            } else {
                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Não possui conteúdo", "");
                FacesContext.getCurrentInstance().addMessage(null, facesMessage);
                return;
            }
        }

        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Salvo com Sucesso", "");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }


    public void deleteCarFolder(Car car, ExternalContext exc) {

        File file = new File(getImagesPathName());

        boolean dir = file.exists();
        if (dir) {
            List<File> fleList = asList(file.listFiles());

            for (File f : fleList) {
                f.delete();
            }
            file.delete();
        }
    }

    public boolean checkFolderContent(String imagespath) {
        return this.imagespath.isEmpty();

    }

    public boolean checkExistentFiles(String fileName, List<String> filesName) {

        return filesName.contains(fileName);


    }

    public List<String> getImagesListOrIOexception(File file, UploadedFile up) {

        try (InputStream ips = up.getInputStream()) {

            Files.copy(ips, new File(file.getAbsolutePath(), up.getFileName()).toPath());

            return listImagesName();

        } catch (IOException e) {
            e.printStackTrace();

            return new ArrayList<>();

        }
    }

    public List<String> listImagesName() {

        File fl = new File(getImagesPathName());
        boolean dir = fl.mkdir();

        if (!dir) {
            List<String> filesNameList = new ArrayList<>();
            File[] fileList = fl.listFiles();

            for (File f : fileList) {
                String filename = f.getName();

                filesNameList.add(filename);
            }
            return filesNameList;

        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", year='" + year + '\'' +
                ", price='" + price + '\'' +
                ", id=" + id +
                ", description='" + description + '\'' +
                ", imagespath='" + imagespath + '\'' +
                ", imagesFolder=" + imagesFolder +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(name, car.name) &&
                Objects.equals(type, car.type) &&
                Objects.equals(brand, car.brand) &&
                Objects.equals(model, car.model) &&
                Objects.equals(year, car.year) &&
                Objects.equals(price, car.price) &&
                Objects.equals(id, car.id) &&
                Objects.equals(description, car.description) &&
                Objects.equals(imagespath, car.imagespath) &&
                Objects.equals(imagesFolder, car.imagesFolder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, brand, model, year, price, id, description, imagespath, imagesFolder);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagespath() {
        return imagespath;
    }

    public void setImagespath(String imagespath) {
        this.imagespath = imagespath;
    }

    public List<String> getImagesFolder() {
        return imagesFolder;
    }

    public void setImagesFolder(List<String> imagesFolder) {
        this.imagesFolder = imagesFolder;
    }
}




