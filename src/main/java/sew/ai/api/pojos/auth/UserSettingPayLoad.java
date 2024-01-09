package sew.ai.api.pojos.auth;

import sew.ai.models.User;

public class UserSettingPayLoad {
    private int UserID;
    private int GroupID;
    private String UtilityAccountNumber;
    private String Search;
    private String ModuleName;
    private String accNumbersKey;
    private int PageSize;
    private int PageNumber;
    private boolean IsEneterpriseUser;
    private String SearchType;
    private boolean IsCSRLogin;

    public UserSettingPayLoad(User user) {
        UserID = Integer.parseInt(user.getUserId());
        GroupID = 0;
        UtilityAccountNumber = user.getDefaultUtilityAccNum();
        Search = "";
        ModuleName = "";
        accNumbersKey = null;
        PageSize = 0;
        PageNumber = 1;
        IsEneterpriseUser = false;
        SearchType = null;
        IsCSRLogin = false;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public int getGroupID() {
        return GroupID;
    }

    public void setGroupID(int groupID) {
        GroupID = groupID;
    }

    public String getUtilityAccountNumber() {
        return UtilityAccountNumber;
    }

    public void setUtilityAccountNumber(String utilityAccountNumber) {
        UtilityAccountNumber = utilityAccountNumber;
    }

    public String getSearch() {
        return Search;
    }

    public void setSearch(String search) {
        Search = search;
    }

    public String getModuleName() {
        return ModuleName;
    }

    public void setModuleName(String moduleName) {
        ModuleName = moduleName;
    }

    public String getAccNumbersKey() {
        return accNumbersKey;
    }

    public void setAccNumbersKey(String accNumbersKey) {
        this.accNumbersKey = accNumbersKey;
    }

    public int getPageSize() {
        return PageSize;
    }

    public void setPageSize(int pageSize) {
        PageSize = pageSize;
    }

    public int getPageNumber() {
        return PageNumber;
    }

    public void setPageNumber(int pageNumber) {
        PageNumber = pageNumber;
    }

    public boolean isIsEneterpriseUser() {
        return IsEneterpriseUser;
    }

    public void setIsEneterpriseUser(boolean isEneterpriseUser) {
        IsEneterpriseUser = isEneterpriseUser;
    }

    public String getSearchType() {
        return SearchType;
    }

    public void setSearchType(String searchType) {
        SearchType = searchType;
    }

    public boolean isIsCSRLogin() {
        return IsCSRLogin;
    }

    public void setIsCSRLogin(boolean isCSRLogin) {
        IsCSRLogin = isCSRLogin;
    }
}
