package org.akollegger.gumdrop;

import javax.swing.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.*;
import java.io.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: akollegger
 * Date: 3/12/13
 * Time: 1:20 AM
 * To change this template use File | Settings | File Templates.
 */
public class GumDragDropListener implements DropTargetListener {
    @Override
    public void dragEnter(DropTargetDragEvent dropTargetDragEvent) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void dragOver(DropTargetDragEvent dropTargetDragEvent) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void dropActionChanged(DropTargetDragEvent dropTargetDragEvent) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void dragExit(DropTargetEvent dropTargetEvent) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void drop(DropTargetDropEvent dropTargetDropEvent) {
        // Accept copy drops
        dropTargetDropEvent.acceptDrop(DnDConstants.ACTION_COPY);

        // Get the transfer which can provide the dropped item data
        Transferable transferable = dropTargetDropEvent.getTransferable();

        // Get the data formats of the dropped item
        DataFlavor[] flavors = transferable.getTransferDataFlavors();

        // Loop through the flavors
        for (DataFlavor flavor : flavors) {

            try {

                // If the drop items are files
                if (flavor.isFlavorJavaFileListType()) {

                    // Get all of the dropped files
                    List<File> files = (List) transferable.getTransferData(flavor);

                    // Loop them through
                    for (File file : files) {
                        JTextArea textArea = (JTextArea) dropTargetDropEvent.getDropTargetContext().getComponent();

                        textArea.append(readContentFrom(file));
                    }

                }

            } catch (Exception e) {

                // Print out the error stack
                e.printStackTrace();

            }
        }

        // Inform that the drop is complete
        dropTargetDropEvent.dropComplete(true);

    }

    private String readContentFrom(File inputFile) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(inputFile));
        StringBuilder sb = new StringBuilder();
        try {
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
        } finally {
            br.close();
        }
        return sb.toString();
    }
}
