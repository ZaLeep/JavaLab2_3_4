package core.customTags;

import core.DBSupport.DAOs.DAOFactory;
import core.DBSupport.connectionPool.ConnectionPool;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.sql.Connection;

public class stationTag extends SimpleTagSupport {

    private int ID;

    private boolean showID = true;

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setShowID(boolean showID) {
        this.showID = showID;
    }

    private String getStationName(int id) {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection connection = cp.getConnection();
        return DAOFactory.getStationDAO(connection).getName(id);
    }

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        String name = getStationName(ID);
        if (showID) {
            out.println(name + " (" + ID + ")");
        } else {
            out.println(name);
        }

    }
}