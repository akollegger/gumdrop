package org.akollegger.gumdrop;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import org.apache.http.client.fluent.Content;

import javax.swing.*;
import java.awt.*;
import java.awt.dnd.DropTarget;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: akollegger
 * Date: 3/11/13
 * Time: 11:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class GumdropApp {
    private JButton postButton;
    private JPanel mainPanel;
    private JTextArea queryResult;
    private JTextArea queryEditor;
    private GumdropAppModel appModel = new GumdropAppModel();
    private GumdropPost dropPost = new GumdropPost();

    public GumdropApp() {
        postButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                getData(appModel);

                try {
                    List<String> results = dropPost.postQueries(new StringReader(appModel.getCypherQuery()));
                    StringBuilder resultBuilder = new StringBuilder();
                    for (String result : results) {
                        resultBuilder.append(result);
                    }
                    appModel.setCypherResult(resultBuilder.toString());
                    setData(appModel);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void display() {
        JFrame frame = new JFrame("GumdropApp");
        frame.setContentPane(mainPanel);
        // Create the drag and drop listener
        GumDragDropListener gumDragDropListener = new GumDragDropListener();

        // Connect the label with a drag and drop listener
        new DropTarget(queryEditor, gumDragDropListener);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }


    public void setData(GumdropAppModel data) {
        queryResult.setText(data.getCypherResult());
        queryEditor.setText(data.getCypherQuery());
    }

    public void getData(GumdropAppModel data) {
        data.setCypherResult(queryResult.getText());
        data.setCypherQuery(queryEditor.getText());
    }

    public boolean isModified(GumdropAppModel data) {
        if (queryResult.getText() != null ? !queryResult.getText().equals(data.getCypherResult()) : data.getCypherResult() != null)
            return true;
        if (queryEditor.getText() != null ? !queryEditor.getText().equals(data.getCypherQuery()) : data.getCypherQuery() != null)
            return true;
        return false;
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(0, 0));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new FormLayout("fill:d:grow", "center:d:grow,top:3dlu:noGrow,center:max(d;4px):noGrow"));
        mainPanel.add(panel2, BorderLayout.SOUTH);
        postButton = new JButton();
        postButton.setText("Post");
        CellConstraints cc = new CellConstraints();
        panel2.add(postButton, cc.xy(1, 3));
        final JSplitPane splitPane1 = new JSplitPane();
        splitPane1.setResizeWeight(0.5);
        mainPanel.add(splitPane1, BorderLayout.CENTER);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new FormLayout("fill:d:grow", "center:d:noGrow,top:3dlu:noGrow,center:d:grow"));
        splitPane1.setRightComponent(panel3);
        final JLabel label1 = new JLabel();
        label1.setText("Query Result");
        panel3.add(label1, cc.xy(1, 1));
        final JScrollPane scrollPane1 = new JScrollPane();
        panel3.add(scrollPane1, cc.xy(1, 3, CellConstraints.FILL, CellConstraints.FILL));
        queryResult = new JTextArea();
        scrollPane1.setViewportView(queryResult);
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new FormLayout("fill:d:grow", "center:d:noGrow,top:3dlu:noGrow,center:d:grow"));
        splitPane1.setLeftComponent(panel4);
        final JLabel label2 = new JLabel();
        label2.setText("Cypher Query");
        panel4.add(label2, cc.xy(1, 1));
        final JScrollPane scrollPane2 = new JScrollPane();
        panel4.add(scrollPane2, cc.xy(1, 3, CellConstraints.FILL, CellConstraints.FILL));
        queryEditor = new JTextArea();
        scrollPane2.setViewportView(queryEditor);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }
}
