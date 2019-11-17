package com.egesenkul.teknotraandroid;

import java.util.ArrayList;

public class WordpressimageApi {
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
    private float author;
    private String comment_status;
    private String ping_status;
    private String template;
    ArrayList < Object > meta = new ArrayList < Object > ();
    Description DescriptionObject;
    Caption CaptionObject;
    private String alt_text;
    private String media_type;
    private String mime_type;
    Media_details Media_detailsObject;
    private float post;
    private String source_url;
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

    public float getAuthor() {
        return author;
    }

    public String getComment_status() {
        return comment_status;
    }

    public String getPing_status() {
        return ping_status;
    }

    public String getTemplate() {
        return template;
    }

    public Description getDescription() {
        return DescriptionObject;
    }

    public Caption getCaption() {
        return CaptionObject;
    }

    public String getAlt_text() {
        return alt_text;
    }

    public String getMedia_type() {
        return media_type;
    }

    public String getMime_type() {
        return mime_type;
    }

    public Media_details getMedia_details() {
        return Media_detailsObject;
    }

    public float getPost() {
        return post;
    }

    public String getSource_url() {
        return source_url;
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

    public void setAuthor(float author) {
        this.author = author;
    }

    public void setComment_status(String comment_status) {
        this.comment_status = comment_status;
    }

    public void setPing_status(String ping_status) {
        this.ping_status = ping_status;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public void setDescription(Description descriptionObject) {
        this.DescriptionObject = descriptionObject;
    }

    public void setCaption(Caption captionObject) {
        this.CaptionObject = captionObject;
    }

    public void setAlt_text(String alt_text) {
        this.alt_text = alt_text;
    }

    public void setMedia_type(String media_type) {
        this.media_type = media_type;
    }

    public void setMime_type(String mime_type) {
        this.mime_type = mime_type;
    }

    public void setMedia_details(Media_details media_detailsObject) {
        this.Media_detailsObject = media_detailsObject;
    }

    public void setPost(float post) {
        this.post = post;
    }

    public void setSource_url(String source_url) {
        this.source_url = source_url;
    }

    public void set_links(_links _linksObject) {
        this._linksObject = _linksObject;
    }
}
class Media_details {
    private float width;
    private float height;
    private String file;
    Sizes SizesObject;
    Image_meta Image_metaObject;


    // Getter Methods

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public String getFile() {
        return file;
    }

    public Sizes getSizes() {
        return SizesObject;
    }

    public Image_meta getImage_meta() {
        return Image_metaObject;
    }

    // Setter Methods

    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public void setSizes(Sizes sizesObject) {
        this.SizesObject = sizesObject;
    }

    public void setImage_meta(Image_meta image_metaObject) {
        this.Image_metaObject = image_metaObject;
    }
}
class Image_meta {
    private String aperture;
    private String credit;
    private String camera;
    private String caption;
    private String created_timestamp;
    private String copyright;
    private String focal_length;
    private String iso;
    private String shutter_speed;
    private String title;
    private String orientation;
    ArrayList < Object > keywords = new ArrayList < Object > ();


    // Getter Methods

    public String getAperture() {
        return aperture;
    }

    public String getCredit() {
        return credit;
    }

    public String getCamera() {
        return camera;
    }

    public String getCaption() {
        return caption;
    }

    public String getCreated_timestamp() {
        return created_timestamp;
    }

    public String getCopyright() {
        return copyright;
    }

    public String getFocal_length() {
        return focal_length;
    }

    public String getIso() {
        return iso;
    }

    public String getShutter_speed() {
        return shutter_speed;
    }

    public String getTitle() {
        return title;
    }

    public String getOrientation() {
        return orientation;
    }

    // Setter Methods

    public void setAperture(String aperture) {
        this.aperture = aperture;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public void setCamera(String camera) {
        this.camera = camera;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public void setCreated_timestamp(String created_timestamp) {
        this.created_timestamp = created_timestamp;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public void setFocal_length(String focal_length) {
        this.focal_length = focal_length;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public void setShutter_speed(String shutter_speed) {
        this.shutter_speed = shutter_speed;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }
}
class Sizes {
    Medium MediumObject;
    Thumbnail ThumbnailObject;
    Full FullObject;


    // Getter Methods

    public Medium getMedium() {
        return MediumObject;
    }

    public Thumbnail getThumbnail() {
        return ThumbnailObject;
    }

    public Full getFull() {
        return FullObject;
    }

    // Setter Methods

    public void setMedium(Medium mediumObject) {
        this.MediumObject = mediumObject;
    }

    public void setThumbnail(Thumbnail thumbnailObject) {
        this.ThumbnailObject = thumbnailObject;
    }

    public void setFull(Full fullObject) {
        this.FullObject = fullObject;
    }
}
class Full {
    private String file;
    private float width;
    private float height;
    private String mime_type;
    private String source_url;


    // Getter Methods

    public String getFile() {
        return file;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public String getMime_type() {
        return mime_type;
    }

    public String getSource_url() {
        return source_url;
    }

    // Setter Methods

    public void setFile(String file) {
        this.file = file;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setMime_type(String mime_type) {
        this.mime_type = mime_type;
    }

    public void setSource_url(String source_url) {
        this.source_url = source_url;
    }
        }
class Thumbnail {
    private String file;
    private float width;
    private float height;
    private String mime_type;
    private String source_url;


    // Getter Methods

    public String getFile() {
        return file;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public String getMime_type() {
        return mime_type;
    }

    public String getSource_url() {
        return source_url;
    }

    // Setter Methods

    public void setFile(String file) {
        this.file = file;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setMime_type(String mime_type) {
        this.mime_type = mime_type;
    }

    public void setSource_url(String source_url) {
        this.source_url = source_url;
    }
}
class Medium {
    private String file;
    private float width;
    private float height;
    private String mime_type;
    private String source_url;


    // Getter Methods

    public String getFile() {
        return file;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public String getMime_type() {
        return mime_type;
    }

    public String getSource_url() {
        return source_url;
    }

    // Setter Methods

    public void setFile(String file) {
        this.file = file;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setMime_type(String mime_type) {
        this.mime_type = mime_type;
    }

    public void setSource_url(String source_url) {
        this.source_url = source_url;
    }
}
class Caption {
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
class Description {
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
class Guid {
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