package estructuras;

import javax.swing.*;
import java.time.LocalDateTime;
import estructuras.VentanaRegistroExpediente;
import estructuras.GestorTramites;
import estructuras.Expediente;
import estructuras.Queue_LL;
import java.time.format.DateTimeFormatter;

public class MenuPrincipal extends JFrame {

    public MenuPrincipal() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Menú Principal - Trámite Documentario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblTitulo = new JLabel("Sistema de Gestión de Trámite Documentario");
        lblTitulo.setBounds(100, 20, 400, 30);
        add(lblTitulo);

        JButton btnRegistrarExpediente = new JButton("Registrar Expediente");
        btnRegistrarExpediente.setBounds(180, 80, 220, 30);
        add(btnRegistrarExpediente);

        JButton btnMovExpediente = new JButton("Registrar Movimiento");
        btnMovExpediente.setBounds(180, 120, 220, 30);
        add(btnMovExpediente);

        JButton btnFinalizar = new JButton("Finalizar Trámite");
        btnFinalizar.setBounds(180, 160, 220, 30);
        add(btnFinalizar);

        JButton btnSeguimiento = new JButton("Consultar Seguimiento");
        btnSeguimiento.setBounds(180, 200, 220, 30);
        add(btnSeguimiento);

        JButton btnVerRuta = new JButton("Ver ruta de dependencias");
        btnVerRuta.setBounds(180, 240, 220, 30);
        add(btnVerRuta);

        JButton btnAlertas = new JButton("Ver Alertas");
        btnAlertas.setBounds(180, 280, 220, 30);
        add(btnAlertas);

        // Funcionalidad: Abrir ventana de registro de expediente
        btnRegistrarExpediente.addActionListener(e -> {
            VentanaRegistroExpediente ventana = new VentanaRegistroExpediente();
            ventana.setLocationRelativeTo(this);
            ventana.setVisible(true);
        });

        // Funcionalidad: Registrar movimiento
        btnMovExpediente.addActionListener(e -> {
            RegistrarMovimiento ventana = new RegistrarMovimiento();
            ventana.setVisible(true);
            ventana.setLocationRelativeTo(this);
        });

        // Funcionalidad: Finalizar trámite
        btnFinalizar.addActionListener(e -> {
            Expediente expedienteFinalizado = null;
            if (!GestorTramites.colaAlta.isEmpty()) {
                expedienteFinalizado = GestorTramites.colaAlta.dequeue();
            } else if (!GestorTramites.colaMedia.isEmpty()) {
                expedienteFinalizado = GestorTramites.colaMedia.dequeue();
            } else if (!GestorTramites.colaBaja.isEmpty()) {
                expedienteFinalizado = GestorTramites.colaBaja.dequeue();
            }

            if (expedienteFinalizado != null) {
                expedienteFinalizado.setFechaFinal(LocalDateTime.now());
                GestorTramites.expedientesFinalizados.insertarFinal(expedienteFinalizado);
                JOptionPane.showMessageDialog(this,
                        "✅ Expediente finalizado:\n\n" + expedienteFinalizado.toString());
            } else {
                JOptionPane.showMessageDialog(this,
                        "⚠️ No hay expedientes pendientes.");
            }
        });

        // Funcionalidad: Ver seguimiento de expedientes finalizados
        btnSeguimiento.addActionListener(e -> {
            if (GestorTramites.expedientesFinalizados.estaVacia()) {
                JOptionPane.showMessageDialog(this, "⚠ No hay expedientes finalizados.");
                return;
            }

            StringBuilder sb = new StringBuilder("📋 Expedientes Finalizados:\n\n");
            Nodo<Expediente> actual = GestorTramites.expedientesFinalizados.getHead();
            while (actual != null) {
                sb.append(actual.getClave().toString()).append("\n\n");
                actual = actual.getSiguiente();
            }

            JTextArea textArea = new JTextArea(sb.toString());
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new java.awt.Dimension(500, 400));
            JOptionPane.showMessageDialog(this, scrollPane, "Seguimiento de Expedientes", JOptionPane.INFORMATION_MESSAGE);
        });

        // Funcionalidad: Ver alertas de pendientes por prioridad
        btnAlertas.addActionListener(e -> {
            StringBuilder sb = new StringBuilder();
            sb.append("📍 PRIORIDAD ALTA\n");
            mostrarCola(GestorTramites.colaAlta, sb);
            sb.append("\n📍 PRIORIDAD MEDIA\n");
            mostrarCola(GestorTramites.colaMedia, sb);
            sb.append("\n📍 PRIORIDAD BAJA\n");
            mostrarCola(GestorTramites.colaBaja, sb);

            JTextArea area = new JTextArea(sb.toString());
            area.setEditable(false);
            JScrollPane scroll = new JScrollPane(area);
            scroll.setPreferredSize(new java.awt.Dimension(400, 300));
            JOptionPane.showMessageDialog(this, scroll, "Expedientes pendientes", JOptionPane.INFORMATION_MESSAGE);
        });

        // Por implementar: Dependencias
        btnVerRuta.addActionListener(e -> {
            if (GestorTramites.rutasDependencias.isEmpty()) {
                JOptionPane.showMessageDialog(this, "⚠ No hay rutas registradas.");
                return;
            }

            StringBuilder sb = new StringBuilder("📌 RUTAS POR DEPENDENCIA:\n\n");

            for (String clave : GestorTramites.rutasDependencias.keySet()) {
                ListaEnlazada<Expediente> lista = GestorTramites.rutasDependencias.get(clave);
                sb.append("📌 ").append(clave.toUpperCase()).append(":\n");

                Nodo<Expediente> actual = lista.getHead();
                while (actual != null) {
                    sb.append(actual.getClave().getId());
                    if (actual.getSiguiente() != null) {
                        sb.append(" -> ");
                    }
                    actual = actual.getSiguiente();
                }
                sb.append(" -> FIN\n\n");
            }

            JTextArea area = new JTextArea(sb.toString());
            area.setEditable(false);
            JScrollPane scroll = new JScrollPane(area);
            scroll.setPreferredSize(new java.awt.Dimension(550, 400));
            JOptionPane.showMessageDialog(this, scroll, "Rutas de Dependencias", JOptionPane.INFORMATION_MESSAGE);
        });
    }

    // Método auxiliar para mostrar el contenido de una cola
    private void mostrarCola(Queue_LL<Expediente> cola, StringBuilder sb) {
        Queue_LL<Expediente>.Node actual = cola.getFront();
        if (actual == null) {
            sb.append(" - (Sin expedientes en esta cola)\n");
            return;
        }
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        while (actual != null) {
            Expediente exp = actual.data;
            sb.append(exp.toString());
            sb.append("\n📅 Fecha de inicio: ")
                    .append(exp.getFechaInicio() != null ? exp.getFechaInicio().format(formato) : "No registrada");
            sb.append("\n--------------------------\n");
            actual = actual.next;
        }
    }

    // Main para lanzar el menú principal
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MenuPrincipal().setVisible(true);
        });
    }
}