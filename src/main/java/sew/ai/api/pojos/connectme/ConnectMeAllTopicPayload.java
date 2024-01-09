package sew.ai.api.pojos.connectme;

public class ConnectMeAllTopicPayload {
    private int TopicType;
    private String LanguageCode;
    private int GroupId;

    public ConnectMeAllTopicPayload(int topicType) {
        this.TopicType = topicType;
        this.LanguageCode = "EN";
        this.GroupId = 0;
    }

    public int getTopicType() {
        return TopicType;
    }

    public void setTopicType(int topicType) {
        TopicType = topicType;
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
