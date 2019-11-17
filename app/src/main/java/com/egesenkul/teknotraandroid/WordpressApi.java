package com.egesenkul.teknotraandroid;

import java.util.ArrayList;

public class WordpressApi {
    private float id;
    private String date;
    private String date_gmt;
    Guid GuidObject;
    private String modified;
    private String modified_gmt;
    private String slug;
    private String status;
    private String type;
    private String link;
    Title TitleObject;
    Content ContentObject;
    Excerpt ExcerptObject;
    private float author;
    private float featured_media;
    private String comment_status;
    private String ping_status;
    private boolean sticky;
    private String template;
    private String format;
    ArrayList < Object > meta = new ArrayList < Object > ();
    ArrayList < Object > categories = new ArrayList < Object > ();
    ArrayList < Object > tags = new ArrayList < Object > ();
    _links _linksObject;


    // Getter Methods

    public float getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getDate_gmt() {
        return date_gmt;
    }

    public Guid getGuid() {
        return GuidObject;
    }

    public String getModified() {
        return modified;
    }

    public String getModified_gmt() {
        return modified_gmt;
    }

    public String getSlug() {
        return slug;
    }

    public String getStatus() {
        return status;
    }

    public String getType() {
        return type;
    }

    public String getLink() {
        return link;
    }

    public Title getTitle() {
        return TitleObject;
    }

    public Content getContent() {
        return ContentObject;
    }

    public Excerpt getExcerpt() {
        return ExcerptObject;
    }

    public float getAuthor() {
        return author;
    }

    public float getFeatured_media() {
        return featured_media;
    }

    public String getComment_status() {
        return comment_status;
    }

    public String getPing_status() {
        return ping_status;
    }

    public boolean getSticky() {
        return sticky;
    }

    public String getTemplate() {
        return template;
    }

    public String getFormat() {
        return format;
    }

    public _links get_links() {
        return _linksObject;
    }

    // Setter Methods

    public void setId(float id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDate_gmt(String date_gmt) {
        this.date_gmt = date_gmt;
    }

    public void setGuid(Guid guidObject) {
        this.GuidObject = guidObject;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public void setModified_gmt(String modified_gmt) {
        this.modified_gmt = modified_gmt;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setTitle(Title titleObject) {
        this.TitleObject = titleObject;
    }

    public void setContent(Content contentObject) {
        this.ContentObject = contentObject;
    }

    public void setExcerpt(Excerpt excerptObject) {
        this.ExcerptObject = excerptObject;
    }

    public void setAuthor(float author) {
        this.author = author;
    }

    public void setFeatured_media(float featured_media) {
        this.featured_media = featured_media;
    }

    public void setComment_status(String comment_status) {
        this.comment_status = comment_status;
    }

    public void setPing_status(String ping_status) {
        this.ping_status = ping_status;
    }

    public void setSticky(boolean sticky) {
        this.sticky = sticky;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public void set_links(_links _linksObject) {
        this._linksObject = _linksObject;
    }
}
class _links {
    ArrayList < Object > self = new ArrayList< Object >();
    ArrayList < Object > collection = new ArrayList < Object > ();
    ArrayList < Object > about = new ArrayList < Object > ();
    ArrayList < Object > author = new ArrayList < Object > ();
    ArrayList < Object > replies = new ArrayList < Object > ();
    ArrayList < Object > curies = new ArrayList < Object > ();


    // Getter Methods



    // Setter Methods


}
class Excerpt {
    private String rendered;


    // Getter Methods

    public String getRendered() {
        return rendered;
    }

    // Setter Methods

    public void setRendered(String rendered) {
        this.rendered = rendered;
    }

}
class Content {
    private String rendered;


    // Getter Methods

    public String getRendered() {
        return rendered;
    }

    // Setter Methods

    public void setRendered(String rendered) {
        this.rendered = rendered;
    }
}
class Title {
    private String rendered;


    // Getter Methods

    public String getRendered() {
        return rendered;
    }

    // Setter Methods

    public void setRendered(String rendered) {
        this.rendered = rendered;
    }
}
