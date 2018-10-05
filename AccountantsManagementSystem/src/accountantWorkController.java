import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.controlsfx.control.spreadsheet.GridBase;
import org.controlsfx.control.spreadsheet.SpreadsheetCell;
import org.controlsfx.control.spreadsheet.SpreadsheetCellType;
import org.controlsfx.control.spreadsheet.SpreadsheetView;

import java.awt.event.ActionEvent;

public class accountantWorkController {

    @FXML  private AnchorPane anchorPane;

    @FXML private SpreadsheetView spreadsheet;

    @FXML private void initialize(){
       /* int rowCount = 5;
        int columnCount = 3;
        GridBase grid = new GridBase(rowCount, columnCount);

        ObservableList<ObservableList<SpreadsheetCell>> rows = FXCollections.observableArrayList();
        for (int row = 0; row < grid.getRowCount(); ++row) {
            final ObservableList<SpreadsheetCell> list = FXCollections.observableArrayList();
            for (int column = 0; column < grid.getColumnCount(); ++column) {
                list.add(SpreadsheetCellType.STRING.createCell(row, column, 1, 1,"value"));
            }
            rows.add(list);
        }
        grid.setRows(rows);
        spreadsheet.setGrid(grid);*/
    }

    //closing the window
    @FXML public void closeWindow()
    {
        Stage stage = (Stage)anchorPane.getScene().getWindow();
        stage.close();
        //System.exit(0);
    }
}
