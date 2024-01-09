package sew.ai.api.pojos.connectme;

public class ConnectMeCityStateProxyPayload {
    String SearchText;
    int Mode;
    String LanguageCode;
    int GroupId;

    public ConnectMeCityStateProxyPayload(int groupId) {
        this.SearchText = "1";
        this.LanguageCode = "EN";
        this.GroupId = groupId;
    }

    public String getSearchText() {
        return SearchText;
    }

    public void setSearchText(String searchText) {
        SearchText = searchText;
    }

    public int getMode() {
        return Mode;
    }

    public void setMode(int mode) {
        Mode = mode;
    }

    public String getLanguageCode() {
        return LanguageCode;
    }

    public void setLanguageCode(String languageCode) {
        LanguageCode = languageCode;
    }

    public int getGroupId() {
        return GroupId;
    }

    public void setGroupId(int groupId) {
        GroupId = groupId;
    }
}
