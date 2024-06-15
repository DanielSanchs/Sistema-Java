/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import bean.Clientes;
import bean.Cursos;
import bean.Usuarios;
import bean.Vendas;
import bean.Vendascursos;
import bean.VendasCursosPesquisa;
import bean.VendasPesquisa;
import bean.VendascursosId;
import dao.ClientesDAO;
import dao.CursosDAO;
import dao.UsuariosDAO;
import dao.VendasCursosDAO;
import dao.VendasDAO;
import static java.lang.System.in;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import tools.Util;

/**
 *
 * @author Daniel Lopes
 */
public class JDlgVendas extends javax.swing.JDialog {

    private ClientesDAO clientesDAO = new ClientesDAO();
    private UsuariosDAO usuariosDAO = new UsuariosDAO();
    public Vendas vendas;
    public VendascursosId vendascursosId = new VendascursosId();
    private VendasDAO vendasDAO = new VendasDAO();
    private VendasCursosDAO vendasCursosDAO = new VendasCursosDAO();
    private boolean incluir;
    private MaskFormatter maskDataVendas;
    private Calendar calendario = Calendar.getInstance();
    public VendasCursosPesquisa vendasCursosPesquisa = new VendasCursosPesquisa();
    public boolean incluirCurso;
    private CursosDAO cursosDAO = new CursosDAO();
    public ArrayList<Vendascursos> listaAtualizar = new ArrayList();
    public ArrayList<Vendascursos> listaExcluir = new ArrayList();
    public ArrayList<Vendascursos> listaInseir = new ArrayList();
    private ArrayList<Vendascursos> vendasCursos = new ArrayList();
    private Util util = new Util();
    private JComponent[] compBotoes;
    private JComponent[] compCampos;

    /**
     * Creates new form JDlgVendas
     */
    public JDlgVendas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setTitle("Vendas");
        setLocationRelativeTo(null);
        vendas = null;
        try {
            maskDataVendas = new MaskFormatter("##/##/####");
            jFmtData.setFormatterFactory(new DefaultFormatterFactory(maskDataVendas));
        } catch (ParseException ex) {
            System.out.println("Deu erro na mascara");
        }

        ArrayList<Clientes> listaClientes = clientesDAO.listAll();
        jCboClientes.removeAllItems();
        for (Clientes c : listaClientes) {
            jCboClientes.addItem(c);
        }
        jCboClientes.setSelectedIndex(-1);

        ArrayList<Usuarios> listaUsuarios = usuariosDAO.listAll();
        jCboUsuarios.removeAllItems();
        for (Usuarios u : listaUsuarios) {
            jCboUsuarios.addItem(u);
        }
        jCboUsuarios.setSelectedIndex(-1);

        ArrayList lista = new ArrayList();
        vendasCursosPesquisa.setList(lista);
        jTable1.setModel(vendasCursosPesquisa);
        
        compCampos = new JComponent[]{jTxtCodigo,jFmtData,jCboClientes,jCboUsuarios,jTxtTotal,jTable1,jBtnIncluirCursos,jBtnAlterarCursos,jBtnExcluirCursos,jBtnConfirmar,jBtnCancelar,};
        compBotoes = new JComponent[]{jBtnIncluir,jBtnAlterar,jBtnExcluir,jBtnPesquisar};
        util.habilitarNovo(false, compCampos);
        
    }

    public void telaBean() {
        Vendas venda = new Vendas();
        calendario.setTime(util.strData(jFmtData.getText()));
        calendario.add(Calendar.DATE, 1);

        venda.setDataVenda(calendario.getTime());
        venda.setIdvendas(util.strInt(jTxtCodigo.getText()));
        venda.setClientes((Clientes) jCboClientes.getSelectedItem());
        venda.setUsuarios((Usuarios) jCboUsuarios.getSelectedItem());
        venda.setTotal(util.strDouble(jTxtTotal.getText()));

        if (incluir) {
            vendasDAO.insert(venda);
            for (int i = 0; i < jTable1.getRowCount(); i++) {
                vendasCursosDAO.insert(vendasCursosPesquisa.getRegistro(i));
            }
        } else {
            vendasDAO.update(venda);
            for (int i = 0; i < listaAtualizar.size(); i++) {
                vendasCursosDAO.update(listaAtualizar.get(i));
            }
            for (int i = 0; i < listaInseir.size(); i++) {
                vendasCursosDAO.insert(listaInseir.get(i));
            }           
            for (int i = 0; i < listaExcluir.size();i++) {               
                vendasCursosDAO.delete(listaExcluir.get(i));
            }
            
//            if (listaExcluidos.size() > 0) {
//                System.out.println(""+listaExcluidos.size());
//                for (int i = 0; i < listaExcluidos.size(); i++) {
//                    vendasCursosDAO.delete(listaExcluidos.get(i));
//                }
//            }
        }        
    }

    public void beanTela(Vendas beanVendas) {
        vendas = beanVendas;

        jTxtCodigo.setText(util.intStr(vendas.getIdvendas()));
        jFmtData.setText(util.dataStr(vendas.getDataVenda()));
        jCboClientes.setSelectedItem(vendas.getClientes());
        jCboUsuarios.setSelectedItem(vendas.getUsuarios());
        jTxtTotal.setText(util.doubleStr(vendas.getTotal()));

        vendasCursos = vendasCursosDAO.listAll(vendas.getIdvendas());
        listaAtualizar = vendasCursosDAO.listAll(vendas.getIdvendas());
        
        //listaAtualizar = (ArrayList<Vendascursos>) vendasCursos;
        vendasCursosPesquisa.setList(vendasCursos);
    }

    public void vendasTotal(double total) {
        jTxtTotal.setText(util.doubleStr(total));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jBtnIncluir = new javax.swing.JButton();
        jBtnAlterar = new javax.swing.JButton();
        jBtnExcluir = new javax.swing.JButton();
        jBtnConfirmar = new javax.swing.JButton();
        jBtnCancelar = new javax.swing.JButton();
        jBtnPesquisar = new javax.swing.JButton();
        jBtnIncluirCursos = new javax.swing.JButton();
        jBtnAlterarCursos = new javax.swing.JButton();
        jBtnExcluirCursos = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jTxtCodigo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jFmtData = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        jCboClientes = new javax.swing.JComboBox<Clientes>();
        jLabel3 = new javax.swing.JLabel();
        jTxtTotal = new javax.swing.JTextField();
        jCboUsuarios = new javax.swing.JComboBox<Usuarios>();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jBtnIncluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/add.png"))); // NOI18N
        jBtnIncluir.setText("Incluir");
        jBtnIncluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnIncluirActionPerformed(evt);
            }
        });
        jPanel1.add(jBtnIncluir);

        jBtnAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/edit.png"))); // NOI18N
        jBtnAlterar.setText("Alterar");
        jBtnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnAlterarActionPerformed(evt);
            }
        });
        jPanel1.add(jBtnAlterar);

        jBtnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/trash_can.png"))); // NOI18N
        jBtnExcluir.setText("Excluir");
        jBtnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnExcluirActionPerformed(evt);
            }
        });
        jPanel1.add(jBtnExcluir);

        jBtnConfirmar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/check_mark.png"))); // NOI18N
        jBtnConfirmar.setText("Confirmar");
        jBtnConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnConfirmarActionPerformed(evt);
            }
        });
        jPanel1.add(jBtnConfirmar);

        jBtnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/cross.png"))); // NOI18N
        jBtnCancelar.setText("Cancelar");
        jBtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(jBtnCancelar);

        jBtnPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/zoom.png"))); // NOI18N
        jBtnPesquisar.setText("Pesquisar");
        jBtnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnPesquisarActionPerformed(evt);
            }
        });
        jPanel1.add(jBtnPesquisar);

        jBtnIncluirCursos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/add.png"))); // NOI18N
        jBtnIncluirCursos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnIncluirCursosActionPerformed(evt);
            }
        });

        jBtnAlterarCursos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/edit.png"))); // NOI18N
        jBtnAlterarCursos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnAlterarCursosActionPerformed(evt);
            }
        });

        jBtnExcluirCursos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/trash_can.png"))); // NOI18N
        jBtnExcluirCursos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnExcluirCursosActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel5.setText("Código");

        jTxtCodigo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTxtCodigoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTxtCodigoFocusLost(evt);
            }
        });

        jLabel1.setText("Data");

        jFmtData.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jFmtDataFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jFmtDataFocusLost(evt);
            }
        });

        jLabel2.setText("Clientes");

        jCboClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCboClientesActionPerformed(evt);
            }
        });

        jLabel3.setText("Total");

        jTxtTotal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTxtTotalFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTxtTotalFocusLost(evt);
            }
        });

        jLabel4.setText("Usuarios");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jBtnIncluirCursos, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jBtnAlterarCursos, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jBtnExcluirCursos, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTxtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jFmtData, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(76, 76, 76)
                                .addComponent(jLabel1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jCboClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jCboUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(97, 97, 97))
                            .addComponent(jTxtTotal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(jBtnIncluirCursos)
                        .addGap(28, 28, 28)
                        .addComponent(jBtnAlterarCursos)
                        .addGap(26, 26, 26)
                        .addComponent(jBtnExcluirCursos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTxtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jFmtData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jCboClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTxtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCboUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)))
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnIncluirActionPerformed
        // TODO add your handling code here:
        util.limparNovo(compCampos);
        vendasCursosPesquisa.setList(new ArrayList());
        incluir = true;
        util.habilitarNovo(true, compCampos);
        util.habilitarNovo(false, compBotoes);
        jTxtCodigo.requestFocusInWindow();
    }//GEN-LAST:event_jBtnIncluirActionPerformed

    private void jBtnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnAlterarActionPerformed
        // TODO add your handling code here:
        if (vendas != null) {
            incluir = false;
            util.habilitarNovo(true, compCampos);
            util.habilitarNovo(false, compBotoes);
            jTxtCodigo.requestFocusInWindow();
        } else {
           util.mostrar("Faça uma pesquisa!");
        }
    }//GEN-LAST:event_jBtnAlterarActionPerformed

    private void jBtnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnExcluirActionPerformed
        // TODO add your handling code here:
        int resp;
        if (vendas != null) {
            resp = util.perguntar("Confirmar e exclusão do registro?", "Remover", JOptionPane.YES_NO_OPTION);
            if (resp == JOptionPane.YES_OPTION) {
                for (int i = 0; i < vendasCursosPesquisa.getRowCount(); i++) {
                    vendasCursosDAO.delete(vendasCursosPesquisa.getRegistro(i));
                }
                vendasDAO.delete(vendas);
                util.limparNovo(compCampos);
                vendasCursosPesquisa.setList(new ArrayList());    
                vendas = null;
            }
        } else {
            util.mostrar("faça uma pesquisa primeiro");
        }
    }//GEN-LAST:event_jBtnExcluirActionPerformed

    private void jBtnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnConfirmarActionPerformed
        // TODO add your handling code here:
        telaBean();
        util.limparNovo(compCampos);
        vendasCursosPesquisa.setList(new ArrayList());
        vendas = null;
        util.habilitarNovo(false, compCampos);
        util.habilitarNovo(true, compBotoes);
        listaAtualizar = new ArrayList();
        listaInseir = new ArrayList();
        listaExcluir = new ArrayList();
    }//GEN-LAST:event_jBtnConfirmarActionPerformed

    private void jBtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCancelarActionPerformed
        // TODO add your handling code here:
        util.limparNovo(compCampos);
        vendasCursosPesquisa.setList(new ArrayList());
        vendas = null;
        util.habilitarNovo(false, compCampos);
        util.habilitarNovo(true, compBotoes);
        listaAtualizar = new ArrayList();
        listaInseir = new ArrayList();
        listaExcluir = new ArrayList();
    }//GEN-LAST:event_jBtnCancelarActionPerformed

    private void jBtnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnPesquisarActionPerformed
        // TODO add your handling code here:
        JDlgVendasPesquisa jDlgVendasPesquisa = new JDlgVendasPesquisa(null, true);
        jDlgVendasPesquisa.setTelaAnterior(this);
        jDlgVendasPesquisa.setVisible(true);
    }//GEN-LAST:event_jBtnPesquisarActionPerformed

    private void jBtnIncluirCursosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnIncluirCursosActionPerformed
        // TODO add your handling code here:

//        ArrayList<Cursos> listaCurso = cursosDAO.listAll();
//        while(listaCurso.size() >= 1){
//            JDlgVendasCursos jDlgVendasCursos = new JDlgVendasCursos(null, true, true, null, 0);
//            jDlgVendasCursos.setTitle("Inclusão");
//            jDlgVendasCursos.setTelaAnterior(this, Integer.parseInt(jTxtCodigo.getText()));
//            jDlgVendasCursos.setVisible(true);
//        }  

        if(vendasCursosPesquisa.getRowCount() < (cursosDAO.listAll()).size()){
            JDlgVendasCursos jDlgVendasCursos = new JDlgVendasCursos(null, true, true, null, 0);
            jDlgVendasCursos.setTitle("Inclusão");
            jDlgVendasCursos.setTelaAnterior(this, util.strInt(jTxtCodigo.getText()));
            jDlgVendasCursos.setVisible(true);
        }
        else{
            util.mostrar("Não tem mais cursos para adicionar a compra");          
        }
    }//GEN-LAST:event_jBtnIncluirCursosActionPerformed

    
    private void jBtnAlterarCursosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnAlterarCursosActionPerformed
        // TODO add your handling code here:
        if (jTable1.getSelectedRow() != -1) {
            Cursos cursos = new Cursos();
            CursosDAO cursosDAO = new CursosDAO();
            int codCursos, codVendas;
            codCursos = (vendasCursosPesquisa.getRegistro(jTable1.getSelectedRow())).getCursos().getIdcursos();
            codVendas = (vendasCursosPesquisa.getRegistro(jTable1.getSelectedRow())).getId().getIdvendas();
            ArrayList<Cursos> listaCursos = cursosDAO.listAll();
            for (Cursos C : listaCursos) {
                if (codCursos == C.getIdcursos()) {
                    cursos = C;
                }
            }
            JDlgVendasCursos jDlgVendasCursos = new JDlgVendasCursos(null, true, false, cursos, codVendas);
            jDlgVendasCursos.setTitle("Alteração");
            jDlgVendasCursos.setTelaAnterior(this, util.strInt(jTxtCodigo.getText()));
            jDlgVendasCursos.setVisible(true);
        } else {
            util.mostrar("Selecione uma linha para alterar");
        }


    }//GEN-LAST:event_jBtnAlterarCursosActionPerformed
    
    public void teste(){
        for(Vendascursos vc : listaAtualizar){
            System.out.println("lista atualizar"+vc);
        }
        for(Vendascursos vc : listaInseir){
            System.out.println("lista inserir"+vc);
        }
        for(Vendascursos vc : listaExcluir){
            System.out.println("lista excluir"+vc);
        }
    }
    
    
    private void jBtnExcluirCursosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnExcluirCursosActionPerformed
        // TODO add your handling code here:
        //listaExcluir.add(vendasCursosPesquisa.getRegistro(jTable1.getSelectedRow()));
        int resp;
        if (jTable1.getSelectedRow() != -1) {
            resp = util.perguntar("Deseja remover o curso?", "remover", JOptionPane.YES_NO_OPTION);
            if (resp == JOptionPane.YES_OPTION) {
                int total = 0, linhaSelecionada;

                //linhaSelecionada = jTable1.getSelectedRow();
                //vendasCursosDAO.delete(vendasCursosPesquisa.getRegistro(linhaSelecionada));
                //listaExcluidos.add(vendasCursosPesquisa.getRegistro(jTable1.getSelectedRow()));
               
                if(listaAtualizar.contains(vendasCursosPesquisa.getRegistro(jTable1.getSelectedRow()))){
                        listaExcluir.add(vendasCursosPesquisa.getRegistro(jTable1.getSelectedRow()));
                        listaAtualizar.remove(vendasCursosPesquisa.getRegistro(jTable1.getSelectedRow()));  
                }else{
                    listaInseir.remove(vendasCursosPesquisa.getRegistro(jTable1.getSelectedRow()));
                }
                teste();
                vendasCursosPesquisa.removeList(jTable1.getSelectedRow());                
                
                for (int i = 0; i < vendasCursosPesquisa.getRowCount(); i++) {
                    total += (vendasCursosPesquisa.getRegistro(i)).getTotal();
                }
                vendasTotal(total);
            }
        } else {
            util.mostrar("Selecione uma linha para remover");
        }
    }//GEN-LAST:event_jBtnExcluirCursosActionPerformed

    private void jTxtCodigoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTxtCodigoFocusGained
        // TODO add your handling code here:
        jTxtCodigo.setBackground(new java.awt.Color(64, 224, 208));
    }//GEN-LAST:event_jTxtCodigoFocusGained

    private void jTxtCodigoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTxtCodigoFocusLost
        // TODO add your handling code here:
        jTxtCodigo.setBackground(java.awt.Color.white);
    }//GEN-LAST:event_jTxtCodigoFocusLost

    private void jFmtDataFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFmtDataFocusGained
        // TODO add your handling code here:
        jFmtData.setBackground(new java.awt.Color(64, 224, 208));
    }//GEN-LAST:event_jFmtDataFocusGained

    private void jFmtDataFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFmtDataFocusLost
        // TODO add your handling code here:
        jFmtData.setBackground(java.awt.Color.white);
    }//GEN-LAST:event_jFmtDataFocusLost

    private void jTxtTotalFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTxtTotalFocusGained
        // TODO add your handling code here:
        jTxtTotal.setBackground(new java.awt.Color(64, 224, 208));
    }//GEN-LAST:event_jTxtTotalFocusGained

    private void jTxtTotalFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTxtTotalFocusLost
        // TODO add your handling code here:
        jTxtTotal.setBackground(java.awt.Color.white);
    }//GEN-LAST:event_jTxtTotalFocusLost

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void jCboClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCboClientesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCboClientesActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JDlgVendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDlgVendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDlgVendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDlgVendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDlgVendas dialog = new JDlgVendas(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnAlterar;
    private javax.swing.JButton jBtnAlterarCursos;
    private javax.swing.JButton jBtnCancelar;
    private javax.swing.JButton jBtnConfirmar;
    private javax.swing.JButton jBtnExcluir;
    private javax.swing.JButton jBtnExcluirCursos;
    private javax.swing.JButton jBtnIncluir;
    private javax.swing.JButton jBtnIncluirCursos;
    private javax.swing.JButton jBtnPesquisar;
    private javax.swing.JComboBox<Clientes> jCboClientes;
    private javax.swing.JComboBox<Usuarios> jCboUsuarios;
    private javax.swing.JFormattedTextField jFmtData;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTxtCodigo;
    private javax.swing.JTextField jTxtTotal;
    // End of variables declaration//GEN-END:variables
}
