package sew.ai.models;

import sew.ai.utils.DataBaseUtils;
import sew.ai.helpers.props.SQLQueries;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GetOutagesPayload {
    int Mode;
    int IsPlannedOutage;
    int offSet;
    String SearchString;
    String LanguageCode;
    int UtilityID;
    int UserID;

    public GetOutagesPayload(int mode, int isPlannedOutage) {
        setMode(mode);
        setIsPlannedOutage(isPlannedOutage);
        setOffSet(-330);
        setSearchString(null);
        setLanguageCode("EN");
        setUtilityID(0);
        setUserID(0);
    }
    
    public GetOutagesPayload(int mode, int isPlannedOutage, String searchString, int userID) {
        setMode(mode);
        setIsPlannedOutage(isPlannedOutage);
        setOffSet(-330);
        setSearchString(searchString);
        setLanguageCode("EN");
        setUtilityID(0);
        setUserID(userID);
    }

    public int getMode() {
        return Mode;
    }

    public void setMode(int Mode) {
        this.Mode = Mode;
    }

    public int getIsPlannedOutage() {
        return IsPlannedOutage;
    }

    public void setIsPlannedOutage(int IsPlannedOutage) {
        this.IsPlannedOutage = IsPlannedOutage;
    }

    public int getOffSet() {
        return offSet;
    }

    public void setOffSet(int offSet) {
        this.offSet = offSet;
    }

    public String getSearchString() {
        return SearchString;
    }

    public void setSearchString(String SearchString) {
        this.SearchString = SearchString;
    }

    public String getLanguageCode() {
        return LanguageCode;
    }

    public void setLanguageCode(String LanguageCode) {
        this.LanguageCode = LanguageCode;
    }

    public int getUtilityID() {
        return UtilityID;
    }

    public void setUtilityID(int UtilityID) {
        this.UtilityID = UtilityID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }
}
