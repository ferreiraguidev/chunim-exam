package academy.devdojo.chunim.bean;

import academy.devdojo.chunim.DAO.CarDAO;
import academy.devdojo.chunim.model.Car;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import static academy.devdojo.chunim.enums.VehiculesType.*;

@Named
@ViewScoped
public class HomeCarList implements Serializable {

    private List<Car> carList;

    private Car selectedcar;



    @PostConstruct
    public void init() {
        carList = CarDAO.search();
    }

    public List<Car> pickUps() {
        return carList.stream().filter(car -> car.getType().equals(PICKUPS.getValue())).collect(Collectors.toList());

    }

    public List<Car> boats() {

        return carList.stream().filter(car -> car.getType().equals(BARCOS.getValue())).collect(Collectors.toList());
    }

    public List<Car> vans() {
        return carList.stream().filter(car -> car.getType().equals(VANS.getValue())).collect(Collectors.toList());

    }

    public List<Car> scooter() {
        return carList.stream().filter(car -> car.getType().equals(BIKES.getValue())).collect(Collectors.toList());
    }

    public List<Car> others() {
        return carList.stream().filter(car -> car.getType().equals(OTHERS.getValue())).collect(Collectors.toList());

    }

    public void selectedCarImage(Car car){
        car.getImagespath();

    }

    public List<Car> getCarList() {
        return carList;
    }


    public void setCarList(List<Car> carList) {
        this.carList = carList;
    }

    public Car getSelectedcar() {
        return selectedcar;
    }

    public void setSelectedcar(Car selectedcar) {
        this.selectedcar = selectedcar;
    }
}
