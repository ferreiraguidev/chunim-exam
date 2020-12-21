//package Util;
//
//import Chunim.Car;
//import org.primefaces.model.file.UploadedFile;
//import org.primefaces.model.file.UploadedFiles;
//
//import javax.enterprise.context.RequestScoped;
//import javax.faces.application.FacesMessage;
//import javax.faces.context.ExternalContext;
//import javax.faces.context.FacesContext;
//import javax.inject.Named;
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.Serializable;
//import java.nio.file.Files;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//
//import static java.util.Arrays.asList;
//
//public class ImgesUtil implements Serializable {
//
//
//    private List<String> imagespath = new ArrayList<>();
//
//    public static String generateFolderName() { // command button to save images and create a folder name
//        return UUID.randomUUID().toString();
//
//    }
//
//    public String getImagesPathName(Car car, ExternalContext exc) { //getting the paths name
//
//        return exc.getRealPath("resources" +
//                File.separator +
//                "default" + File.separator + "CarsFolders" + File.separator + car.getImagespath());
//    }
//
//    public void uploadImagesToFolders(UploadedFiles ups, File file) { //uploading the images.
//
//        boolean aux = file.mkdirs();
//
//        for (UploadedFile upf : ups.getFiles()) {
//
//            if (!checkExistentFiles(upf.getFileName(), imagespath)) {
//
//                imagespath = getImagesListOrIOexception(file, upf);
//
//            } else {
//                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,
//                        "Doesent have any content", "");
//                FacesContext.getCurrentInstance().addMessage(null, facesMessage);
//                return;
//            }
//        }
//        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO,
//                "Successfully Saved", "");
//        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
//    }
//
//    public void deleteCarFolder(Car car, ExternalContext exc) {
//
//        File file = new File(getImagesPathName(car, exc));
//
//        boolean dir = file.exists();
//        if (dir) {
//            List<File> fleList = asList(file.listFiles());
//
//            for (File f : fleList) {
//                f.delete();
//            }
//            file.delete();
//        }
//    }
//
//    public boolean checkFolderContent() {
//        return imagespath.isEmpty();
//
//    }
//
//    private boolean checkExistentFiles(String fileName, List<String> filesName) {
//
//        return filesName.contains(fileName);
//
//    }
//
//    private List<String> getImagesListOrIOexception(File file, UploadedFile up) {
//
//        try (InputStream ips = up.getInputStream()) {
//
//            Files.copy(ips, new File(file.getAbsolutePath(), up.getFileName()).toPath());
//
//            return listImagesName(file);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//
//            return new ArrayList<>();
//        }
//    }
////
////    public List<String> listImagesName(File fl) {
////        boolean dir = fl.mkdir();
////
////        if (!dir) {
////            List<String> filesNameList = new ArrayList<>();
////            File[] list = fl.listFiles();
////            for (File f : list) {
////                filesNameList.add(f.getName());
////            }
////            return filesNameList;
////        } else {
////            return new ArrayList<>();
////        }
////
//    }
//
