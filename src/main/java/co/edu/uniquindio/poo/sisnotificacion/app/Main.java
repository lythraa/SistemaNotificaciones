package co.edu.uniquindio.poo.sisnotificacion.app;

import co.edu.uniquindio.poo.sisnotificacion.controller.AdminController;
import co.edu.uniquindio.poo.sisnotificacion.controller.ClienteController;
import co.edu.uniquindio.poo.sisnotificacion.controller.ModeradorController;
import co.edu.uniquindio.poo.sisnotificacion.model.RefrescarTodo;
import co.edu.uniquindio.poo.sisnotificacion.model.SistemaNotificaciones;
import co.edu.uniquindio.poo.sisnotificacion.model.TemplateMethod.AdminUser;
import co.edu.uniquindio.poo.sisnotificacion.model.TemplateMethod.ClientUser;
import co.edu.uniquindio.poo.sisnotificacion.model.TemplateMethod.ModeratorUser;
import co.edu.uniquindio.poo.sisnotificacion.model.TemplateMethod.User;
import co.edu.uniquindio.poo.sisnotificacion.model.strategy.EmailNotification;
import co.edu.uniquindio.poo.sisnotificacion.model.strategy.PushNotification;
import co.edu.uniquindio.poo.sisnotificacion.model.strategy.SMSNotification;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

        @Override
        public void start(Stage primaryStage) throws Exception {
            ClientUser kevin = new ClientUser("Kevin", "kevin@example.com", new EmailNotification());
            AdminUser alice = new AdminUser("Alice", "alice@admin.com", new SMSNotification());
            ModeratorUser bob = new ModeratorUser("Bob", "bob@mod.com", new PushNotification());

            List<User> usuarios = new ArrayList<>();
            usuarios.add(kevin);
            usuarios.add(alice);
            usuarios.add(bob);

            SistemaNotificaciones sistema = SistemaNotificaciones.getInstancia();
            sistema.setListaUsuarios(usuarios);

            sistema.setClienteActual(kevin);
            sistema.setAdminActual(alice);
            sistema.setModeradorActual(bob);

            FXMLLoader adminLoader = new FXMLLoader(getClass().getResource("/views/AdminView.fxml"));
            Parent adminRoot = adminLoader.load();
            AdminController adminController = adminLoader.getController();  // <-- Obtienes el controller
            Stage adminStage = new Stage();
            adminStage.setTitle("Administrador");
            adminStage.setScene(new Scene(adminRoot));
            adminStage.show();

            FXMLLoader clienteLoader = new FXMLLoader(getClass().getResource("/views/ClienteView.fxml"));
            Parent clienteRoot = clienteLoader.load();
            ClienteController clienteController = clienteLoader.getController(); // <-- Obtienes el controller
            Stage clienteStage = new Stage();
            clienteStage.setTitle("Cliente");
            clienteStage.setScene(new Scene(clienteRoot));
            clienteStage.show();

            FXMLLoader moderadorLoader = new FXMLLoader(getClass().getResource("/views/ModeradorView.fxml"));
            Parent moderadorRoot = moderadorLoader.load();
            ModeradorController moderadorController = moderadorLoader.getController();  // <-- Obtienes el controller
            Stage moderadorStage = new Stage();
            moderadorStage.setTitle("Moderador");
            moderadorStage.setScene(new Scene(moderadorRoot));
            moderadorStage.show();

            RefrescarTodo.setAdminController(adminController);
            RefrescarTodo.setClienteController(clienteController);
            RefrescarTodo.setModeradorController(moderadorController);

        }

        public static void main(String[] args) {
            launch(args);
        }
    }
