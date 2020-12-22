package Chunim;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;


@Named
@SessionScoped
public class HomeCarList implements Serializable {

    private List<Car> carList;
    private Car selectedcar;


    @PostConstruct
    public void init(){
        carList = CarDAO.search();
    }

    public List<Car> pickUps() {
        return carList.stream().filter(car -> car.getType().equals("pickup")).collect(Collectors.toList());

    }

    public List<Car> boats() {

        return carList.stream().filter(car -> car.getType().equals("barcos")).collect(Collectors.toList());
    }


    public List<Car> vans() {
        return carList.stream().filter(car -> car.getType().equals("vans")).collect(Collectors.toList());

    }

    public List<Car> standardCars() {
        return carList.stream().filter(car -> car.getType().equals("zero")).collect(Collectors.toList());

    }

    public List<Car> others() {
        return carList.stream().filter(car -> car.getType().equals("outros")).collect(Collectors.toList());

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
