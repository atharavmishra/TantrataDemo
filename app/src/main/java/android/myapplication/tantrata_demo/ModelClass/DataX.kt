package android.myapplication.tantrata_demo.ModelClass

data class DataX(
    var account_id: Int,
    var account_url: String,
    var ad_config: AdConfig,
    var ad_type: Int,
    var ad_url: String,
    var comment_count: Int,
    var cover: String,
    var cover_height: Int,
    var cover_width: Int,
    var datetime: Int,
    var description: Any,
    var downs: Int,
    var favorite: Boolean,
    var favorite_count: Int,
    var id: String,
    var images: List<Image>,
    var images_count: Int,
    var in_gallery: Boolean,
    var in_most_viral: Boolean,
    var include_album_ads: Boolean,
    var is_ad: Boolean,
    var is_album: Boolean,
    var layout: String,
    var link: String,
    var nsfw: Boolean,
    var points: Int,
    var privacy: String,
    var score: Int,
    var section: String,
    var tags: List<Tag>,
    var title: String,
    var topic: Any,
    var topic_id: Any,
    var ups: Int,
    var views: Int,
    var vote: Any
)