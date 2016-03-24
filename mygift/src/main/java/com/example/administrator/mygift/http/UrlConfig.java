package com.example.administrator.mygift.http;

/**
 * Created by Administrator on 16-3-16.
 */
public class UrlConfig {
    public static final String GUIDE_SELECT_TAB_URL =
            "http://api.liwushuo.com/v2/channels/preset?gender=1&generation=2";
    public static final String GUIDE_SELECT_EXPANDLIST_URL =
            "http://api.liwushuo.com/v2/channels/101/items?ad=2&gender=1&generation=2&limit=20&offset=0";
    public static final String GUIDE_SELECT_BANNER_URL =
            "http://api.liwushuo.com/v2/banners";
    public static final String GUIDE_SELECT_HORIZENTAl_RECYLEVIEW_URL =
            "http://api.liwushuo.com/v2/secondary_banners?gender=1&generation=1";
    public static final String HOT_GRIDVIEW_URL =
            "http://api.liwushuo.com/v2/items?gender=1&limit=20&offset=0&generation=2";
    public static final String STRATEGY_EXPANDLIST_URL =
            "http://api.liwushuo.com/v2/channel_groups/all";
    public static final String STRATEGY_HEADER_URL =
            "http://api.liwushuo.com/v2/collections?limit=10&offset=0";
}
