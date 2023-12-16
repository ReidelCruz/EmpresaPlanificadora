package Vista;

import CRUD.GestionDeDatos;
import Producto.Producto;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Interface extends JFrame implements ActionListener {

    JButton botonOpcion1 = new JButton("Obtener Lista");
    JButton botonOpcion2 = new JButton("Añadir Producto");
    JButton botonOpcion3 = new JButton("Eliminar Producto");
    JButton botonOpcion4 = new JButton("Modificar Producto");

    public Interface() {
        this.setLayout(null);
        this.setSize(1024, 800);
        this.setTitle("Empresa Planificadora");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(100, 100, 600, 400);
        GridLayout manager = new GridLayout();
        manager.setHgap(25);
        manager.setVgap(25);
        manager.setRows(4);
        manager.setColumns(1);
        mainPanel.setLayout(manager);

        botonOpcion1.addActionListener(this);
        botonOpcion2.addActionListener(this);
        botonOpcion3.addActionListener(this);
        botonOpcion4.addActionListener(this);

        this.add(mainPanel);
        mainPanel.add(botonOpcion1);
        mainPanel.add(botonOpcion2);
        mainPanel.add(botonOpcion3);
        mainPanel.add(botonOpcion4);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonOpcion1) {
            try {
                Mensaje(ObtenerListado(GestionDeDatos.CargarDatos()));
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        if (e.getSource() == botonOpcion2) {
            AgregarProducto();
        }
        if (e.getSource() == botonOpcion3) {
            String ref = JOptionPane.showInputDialog("Introduzca la referencia del producto a eliminar: ");
            try {
                EliminarProducto(ref);
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        if (e.getSource() == botonOpcion4) {
            String ref = JOptionPane.showInputDialog("Introduzca la referencia del producto a modificar: ");
            try {
                ModificarProducto(ref);
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

    }

    public void AgregarProducto() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(new FlowLayout());

        JTextField campoReferencia = new JTextField("Referencia");
        campoReferencia.setSize(new Dimension(100, 100));
        JTextField campoParticipacion = new JTextField("Participacion");
        campoParticipacion.setSize(new Dimension(100, 100));

        JButton boton = new JButton("Añadir Producto");
        boton.addActionListener(e -> {
            try {
                GuardarProducto(new Producto(campoReferencia.getText(),
                        campoParticipacion.getText()));
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        frame.add(campoReferencia);
        frame.add(campoParticipacion);
        frame.add(boton);

        frame.pack();
        frame.setVisible(true);
        frame.getContentPane().setBackground(Color.WHITE);

    }

    public void ModificarProducto(String referenciaProducto) throws FileNotFoundException, IOException {

        Producto p = null;
        Vector<Producto> vector = GestionDeDatos.CargarDatos();

        for (Producto producto : vector) {
            if (producto.GetReferencia().equals(referenciaProducto)) {
                p = producto;
                vector.remove(p);
                GestionDeDatos.GuardarDatos(vector);
                break;
            }
        }

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(new FlowLayout());

        JTextField campoReferencia = new JTextField("Referencia");
        campoReferencia.setSize(new Dimension(100, 100));
        campoReferencia.setText(referenciaProducto);
        JTextField campoParticipacion = new JTextField("Participacion");
        campoParticipacion.setSize(new Dimension(100, 100));
        campoParticipacion.setText(p.GetParticipacionProduccion());

        JButton boton = new JButton("Modificar Producto");
        boton.addActionListener(e -> {
            try {
                GuardarProducto(new Producto(campoParticipacion.getText(),
                        campoReferencia.getText()));
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        frame.add(campoReferencia);
        frame.add(campoParticipacion);
        frame.add(boton);

        frame.pack();
        frame.setVisible(true);
        frame.getContentPane().setBackground(Color.WHITE);

    }

    // Mostrar listado
    public static String ObtenerListado(Vector<Producto> products) {
        String str = "";
        for (int i = 0; i < products.size(); i++) {
            str += (products.get(i).GetReferencia() + " " + products.get(i).GetParticipacionProduccion() + "\n");
        }
        System.out.println(str);
        return str;
    }

    private void GuardarProducto(Producto producto) throws FileNotFoundException, IOException {
        Vector<Producto> vector = GestionDeDatos.CargarDatos();
        vector.add(producto);
        GestionDeDatos.GuardarDatos(vector);
        Mensaje("Producto creado con exito!");
    }

    private void EliminarProducto(String ref) throws FileNotFoundException, IOException {

        Producto p = null;

        Vector<Producto> vector = GestionDeDatos.CargarDatos();
        for (Producto producto : vector) {
            if (producto.GetReferencia().equals(ref)) {
                p = producto;
                break;
            }
        }
        vector.remove(p);
        GestionDeDatos.GuardarDatos(vector);
        Mensaje("Producto eliminado con exito!");
    }

    public void Mensaje(String mensaje) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(new GridLayout(0, 1));
        frame.setMinimumSize(new Dimension(100, 100));

        String[] lines = mensaje.split("\n");

        for (String line : lines) {
            System.out.println(line);
            JLabel label = new JLabel(line);
            frame.add(label);
        }
        frame.setVisible(true);
        frame.pack();

    }
}
