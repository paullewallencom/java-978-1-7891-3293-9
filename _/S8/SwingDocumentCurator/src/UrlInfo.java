/**
 * Created by vitthalsrinivasan on 11/10/15.
 */
public class UrlInfo {

    private String url;
    private String headline;
    private String summary;

    public UrlInfo(String url) {
        this.url = url;
        this.headline = url;
        this.summary = "";
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
