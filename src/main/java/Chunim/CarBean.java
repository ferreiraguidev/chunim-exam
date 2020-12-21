package Chunim;

import org.primefaces.model.file.UploadedFiles;


import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static Chunim.Car.generateFolderName;
import static Chunim.CarDAO.edit;
import static Chunim.CarDAO.save;

@Named
@SessionScoped
public class CarBean implements Serializable {

    private Car car = new Car();
    private List<Car> cars = new ArrayList<>();
    private CarDAO carDAO = new CarDAO();

    private Car selectedCar;

    ///////

    private List<String> imagesFolder;

    private final ExternalContext externalContext;

    private UploadedFiles uploadedFiles;

    @PostConstruct
    public void init() {
        cars = carDAO.search();

    }

    @Inject
    public CarBean(ExternalContext externalContext) {
        this.externalContext = externalContext;
    }

    public void add() {

        if (car.getId() == null) {
            save(car);

            System.out.println("saved data");
        } else {
            edit(car);
            System.out.println("Successfully updated in dbms");

        }
    }

    public void upload() {

        car.setImagespath(generateFolderName()); // static importation

        if (uploadedFiles != null) {

            File filedoc = new File(car.getImagesPathName());

            car.uploadImagesToFolders(uploadedFiles, filedoc);

        }
    }

    public List<String> getImagesFolder() {
        return imagesFolder;
    }

    public void setImagesFolder(List<String> imagesFolder) {
        this.imagesFolder = imagesFolder;
    }

    public void list() {
        cars = carDAO.search();
        car.getImagespath();

    }
    public void listSelected(){

        cars = carDAO.searchByBrand(getCar().getBrand());
        car.getImagespath();

    }

    public void DAOedit(Car auto) {
        car = auto;

    }

    public void delete(Car car) {
        CarDAO.delete(car);
        car.deleteCarFolder(car, externalContext);
    }

    public void searchByBrand() {

        cars = carDAO.searchByBrand(car.getBrand());
        car.getBrand();

    }

    public Car getSelectedCar() {
        return selectedCar;
    }

    public void setSelectedCar(Car selectedCar) {
        this.selectedCar = selectedCar;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public CarDAO getCarDAO() {
        return carDAO;
    }

    public void setCarDAO(CarDAO carDAO) {
        this.carDAO = carDAO;
    }

    public ExternalContext getExternalContext() {
        return externalContext;
    }

    public UploadedFiles getUploadedFiles() {
        return uploadedFiles;
    }

    public void setUploadedFiles(UploadedFiles uploadedFiles) {
        this.uploadedFiles = uploadedFiles;
    }

    public Car getCarro() {
        return car;
    }

    public void setCarro(Car carro) {
        this.car = carro;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

}

