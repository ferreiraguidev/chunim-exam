package academy.devdojo.chunim.bean;

import academy.devdojo.chunim.DAO.CarDAO;
import academy.devdojo.chunim.model.Car;
import org.primefaces.model.file.UploadedFiles;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static academy.devdojo.chunim.DAO.CarDAO.edit;
import static academy.devdojo.chunim.DAO.CarDAO.save;
import static academy.devdojo.chunim.model.Car.generateFolderName;


@Named
@ViewScoped
public class CarBean implements Serializable {

    private Car car = new Car();
    private List<Car> cars = new ArrayList<>();
    private CarDAO carDAO = new CarDAO();
    private Car selectedCar;


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

            car = new Car();

            System.out.println("saved data");

        } else {
            edit(car);
            System.out.println("Successfully updated in dbms");

        }
    }

    public void upload() {

        car.setImagespath(generateFolderName());

        if (uploadedFiles != null) {

            File filedoc = new File(car.getImagesPathName());
            car.uploadImagesToFolders(uploadedFiles, filedoc);

        }

        add();
    }

    public List<Car> list() {
        cars = carDAO.search();
        car.getImagespath();

        car = new Car();

        return carDAO.search();

    }

    public void DAOedit(Car auto) {
        car = auto;

    }

    public void delete(Car car) {

        CarDAO.delete(car);
        car.deleteCarFolder(car, externalContext);

    }

    public void reload() throws IOException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
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

