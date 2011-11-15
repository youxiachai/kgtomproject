package kg.tom.utils;



import kg.tom.R;
import android.os.Environment;
import android.provider.MediaStore;

public class ConstantsVar {
	/** UI States **/
	public static final int STATE_INTRO = -1;
	public static final int STATE_NAVIGATOR = 0;
	public static final int STATE_FULLSCREEN = 1;
	
	/** Browse Categories **/
	public static final int BROWSECAT_ARTIST = 0;
	public static final int BROWSECAT_ALBUM = 1;
	public static final int BROWSECAT_SONG = 2;
	public static final int BROWSECAT_GENRE = 3;
	public static final int BROWSECAT_PLAYLIST = 4;

	/** Renderer Types */
	public static final int RENDERER_CUBE = 0;
	public static final int RENDERER_WALL = 1;
	public static final int RENDERER_BORING = 2;
	public static final int RENDERER_MORPH = 3;
	
	/** Theme Types */
	public static final int THEME_NORMAL = 100; // these should not coincide with the Renderer modes
	public static final int THEME_HALFTONE = 101;
	public static final int THEME_EARTHQUAKE = 102;
	
	/** Half Tone Theme */
	public static final int 	THEME_HALF_TONE_PROC_RESOLUTION = 640; 
	public static final int 	THEME_HALF_TONE_BLOCK_COUNT = 64; 
	public static final String THEME_HALF_TONE_FILE_EXT = ".halftone";

	/** Earthquake Theme */
	public static final int	THEME_EARTHQUAKE_BLOCK_COUNT = 256;
	public static final int	THEME_EARTHQUAKE_RANDOM_AMOUNT = 16;
	public static final String THEME_EARTHQUAKE_FILE_EXT = ".earthquake";
	
	/** Playlist Ids **/
	public static final int PLAYLIST_UNKNOWN = -1; // uninitialized variable
	public static final int PLAYLIST_ALL = -2;
	public static final int PLAYLIST_MOST_RECENT = -3;
	public static final int PLAYLIST_MOST_PLAYED = -4;
	public static final int PLAYLIST_GENRE_OFFSET = -100000;
	public static final int PLAYLIST_GENRE_RANGE = 5000;
	
	/** Specific Constants */
	public static final int NO_SPECIFIC_ARTIST = -1;
	
	/** and keys */
	public static final String	PLAYLIST_ID_KEY = "playlistId";
	public static final String PLAYLIST_NAME_KEY = "playlistName";

	
	/** Album Cursor Projection **/
	public static final String[] albumProjection = 
	{
		MediaStore.Audio.Albums._ID,
		MediaStore.Audio.Albums.ALBUM_KEY,
//		MediaStore.Audio.Albums.ALBUM_ID,
		MediaStore.Audio.Albums.ALBUM,
		MediaStore.Audio.Albums.ARTIST,
		MediaStore.Audio.Albums.ALBUM_ART,
		MediaStore.Audio.Albums.LAST_YEAR
	};
	
	/** Artist Cursor Projection */
	public static final String[] artistProjection = 
	{
		MediaStore.Audio.Artists._ID,
		MediaStore.Audio.Artists.ARTIST_KEY,
		MediaStore.Audio.Artists.ARTIST,
		MediaStore.Audio.Artists.NUMBER_OF_ALBUMS,
		MediaStore.Audio.Artists.NUMBER_OF_TRACKS
	};
	
	/** Song Cursor Projection **/
	public static final String[] songProjection = 
	{
		MediaStore.Audio.Media._ID,
		MediaStore.Audio.Media.ALBUM,
		MediaStore.Audio.Media.ALBUM_ID,
		MediaStore.Audio.Media.ALBUM_KEY,
		MediaStore.Audio.Media.ARTIST,
		MediaStore.Audio.Media.ARTIST_ID,
		MediaStore.Audio.Media.TITLE,
		MediaStore.Audio.Media.TITLE_KEY,
		MediaStore.Audio.Media.DATA,
		MediaStore.Audio.Media.DISPLAY_NAME,
		MediaStore.Audio.Media.DURATION,
		MediaStore.Audio.Media.IS_MUSIC,
		MediaStore.Audio.Media.TRACK
	};
	
	/** Genres Cursor Projection **/
	public static final String[] genreProjection = 
	{
		MediaStore.Audio.Genres._ID,
		MediaStore.Audio.Genres.NAME
	};
	
	/** Genres Cursor Projection */
	public static final String[] genreMemberProjection = 
	{
		MediaStore.Audio.Genres.Members._ID,
//		MediaStore.Audio.Genres.Members.AUDIO_ID,
//		MediaStore.Audio.Genres.Members.GENRE_ID,
		MediaStore.Audio.Genres.Members.ALBUM_ID,
		MediaStore.Audio.Genres.Members.ALBUM,
		MediaStore.Audio.Genres.Members.ARTIST,
		MediaStore.Audio.Genres.Members.ARTIST_ID,
		MediaStore.Audio.Genres.Members.TITLE,
		MediaStore.Audio.Genres.Members.TRACK,
		MediaStore.Audio.Genres.Members.DURATION
	};
	
	/** Playlist Cursor Projection **/
	public static final String[] playlistProjection = 
	{
		MediaStore.Audio.Playlists._ID,
		MediaStore.Audio.Playlists.NAME
	};
	
	/** Playlist Members Cursor Projection */
	public static final String[] playlistMembersProjection = 
	{
		MediaStore.Audio.Playlists.Members._ID,
		MediaStore.Audio.Playlists.Members.PLAYLIST_ID,
//			MediaStore.Audio.Playlists.Members.ALBUM_ART,
		MediaStore.Audio.Playlists.Members.ALBUM_ID,
		MediaStore.Audio.Playlists.Members.ALBUM,
		MediaStore.Audio.Playlists.Members.ARTIST,
		MediaStore.Audio.Playlists.Members.ARTIST_ID,
		MediaStore.Audio.Playlists.Members.AUDIO_ID,
		MediaStore.Audio.Playlists.Members.TITLE,
		MediaStore.Audio.Playlists.Members.TRACK,
		MediaStore.Audio.Playlists.Members.DURATION
	};
	
	/** Genre Member Alphabetical Sorting **/
	public static final String genreMembersAlbumSorting = 
		MediaStore.Audio.Genres.Members.ALBUM_ID + " ASC";
	
	/** Genre Alphabetical Sorting **/
	public static final String genreAlphabeticalSorting = 
		MediaStore.Audio.Genres.NAME + " COLLATE NOCASE ASC";
	
	/** Playlist Members Alphabetical Album Sorting **/
	public static final String playlistMembersAlbumSorting = 
		MediaStore.Audio.Playlists.Members.ALBUM_ID + " ASC";
	
	/** Playlist Alphabetical Sorting **/
	public static final String playlistAlphabeticalSorting = 
		MediaStore.Audio.Playlists.NAME + " COLLATE NOCASE ASC";
	
	/** Album Cursor Sorting **/
	public static final String albumAlphabeticalSortOrder = 
		MediaStore.Audio.Albums.ALBUM_KEY + " ASC";
	
	public static final String albumAlphabeticalSortOrderByArtist = 
		MediaStore.Audio.Albums.ARTIST + " COLLATE NOCASE ASC"
		+ ", " + 
		MediaStore.Audio.Albums.LAST_YEAR + " DESC";
	
	/** Artist Albums Cursor Sorting **/
	public static final String artistAlbumsYearSortOrder = 
		MediaStore.Audio.Albums.LAST_YEAR + " DESC";

	/** Artist Cursor Sorting **/
	public static final String artistAlphabeticalSortOrder = 
		MediaStore.Audio.Artists.ARTIST_KEY + " ASC";
//		MediaStore.Audio.Albums.ARTIST + " COLLATE NOCASE ASC";
	
	/** Song List Sorting **/
	public static final String songListNumericalSorting = 
		MediaStore.Audio.Media.TRACK + " ASC";
	public static final String songListAlbumAndNumericalSorting = 
//		MediaStore.Audio.Media.YEAR + " DESC " + 
//		", "+
		MediaStore.Audio.Media.ALBUM + " COLLATE NOCASE ASC"+
		", "+
		MediaStore.Audio.Media.TRACK + " ASC";
	public static final String songListPlaylistSorting = 
		MediaStore.Audio.Playlists.Members.PLAY_ORDER + " ASC";
	public static final String songListTitleSorting =
//		MediaStore.Audio.Media.TITLE + " COLLATE NOCASE ASC";
		MediaStore.Audio.Media.TITLE_KEY + " ASC";

	
	/** song list adapter to/from */
//	public static final int	albumSongListLayoutId = R.layout.songlist_dialog_item;
	public static final String[] albumSongListFrom = {
		MediaStore.Audio.Media.TITLE,
		MediaStore.Audio.Media.DURATION
	};
//	public static final int[]	albumSongListTo = {
//		R.id.songlist_item_song_name,
//		R.id.songlist_item_song_duration
//	};
	

	
	/** song list adapter for current playing lists */
//	public static final int	queueSongListLayoutId = R.layout.songlist_dialog_item_queue;
//	public static final String[] queueSongListFrom = {
//		MediaStore.Audio.Media.TITLE,
//		MediaStore.Audio.Media.ARTIST,
//		MediaStore.Audio.Media.DURATION
//	};
//	public static final int[]	queueSongListTo = {
//		R.id.queue_list_songname,
//		R.id.queue_list_artistname,
//		R.id.queue_list_songduration
//	};
	
//	/** UI Dimension proportions */
//	public static final float navItemMinFraction = 0.6f; // minimum fraction of the smallest screen dimension
	
	/** Inactivity intervals */
	public static final double	MAX_INACTIVITY_INTERVAL_TO_MAINTAIN_STATE = 10 * 1000; // 10 secs
	public static final double MAX_INACTIVITY_INTERVAL_TO_MAINTAIN_PLAYLIST = 60 * 60 * 12 * 1000; // 12 hours
	
	/** UI scrolling parameters */
	public static final double	FRAME_DURATION_STD = 40;
	public static final double	FRAME_JUMP_MAX = 10;
	public static final float	SCROLL_SPEED_SMOOTHNESS = 2.5f; // as the fraction of the overall animation that should be obtained (per second)
	public static final float	CPU_SMOOTHNESS = 0.1f; // as the fraction of the overall animation that should be obtained (per second)
	public static final float	MIN_SCROLL = 1.5f; // as the fraction of the cover size (per second)
//	public static final float	MAX_SCROLL = 6.f; // as the fraction of the cover size (per second)
	public static final float	MAX_SCROLL = 9.f; // as the fraction of the cover size (per second)
	public static final float	SCROLL_SPEED_BOOST = 675.f;
//	public static final float	MAX_LOW_SPEED = .08f; // mScrollingSpeed...
	public static final float	MAX_LOW_SPEED = .0025f; // mScrollingSpeed...
	public static final float	MIN_SCROLL_TOUCH_MOVE = 0.05f;
	public static final double	MAX_CLICK_DOWNTIME = 1000;
	public static final int 	MIN_LONG_CLICK_DURATION = 1000;
	public static final int	MAX_POSITION_OVERSHOOT = 1;
	public static final int	SCROLLING_RESET_TIMEOUT = 7500;
		
	/** CLICK MODES */
	public static final int	SINGLE_CLICK = 0;
	public static final int	LONG_CLICK = 1;
	
	/** UI scrolling modes */
	public static final int	SCROLL_MODE_VERTICAL = 0;
	public static final int	SCROLL_MODE_HORIZONTAL = 1;
	
	/** UI element IDs */
	public static final int ALBUM_NAV_VIEW_ID = 0;
	public static final int ALBUM_NAV_CONTROLS_ID = 1;
	public static final int ALBUM_NAV_INFO_ID = 2;
	
	/** Album Artist Switcher stuff */
	public static final int 	SWITCHER_PERSIST_SWITCH_PERIOD = 750;
	public static final int 	SWITCHER_HIGH_PRESENCE_ALPHA = 192;
	public static final float 	SWITCHER_MOVEMENT_REQUIRED_TO_SWITCH = .25f;
	public static final int 	SWITCHER_LOW_PRESENCE_ALPHA = 0;
	public static final float 	SWITCHER_PRESENCE_UPDATE_STEP = .1f; // increment to navigate between 0 and 1
	public static final float	SWITCHER_TEXT_RATIO = .66f;
	public static final float	SWITCHER_CAT_CIRCLE_RATIO = .05f;
	public static final float	SWITCHER_CAT_CIRCLE_SPACING = 1.f;
	public static final int	SWITCHER_CAT_ALBUM = BROWSECAT_ALBUM;
	public static final int	SWITCHER_CAT_ARTIST = BROWSECAT_ARTIST;
	public static final int	SWITCHER_CAT_SONG = BROWSECAT_SONG;
	public static final int	SWITCHER_CATEGORY_COUNT = 3; // ALBUMS + ARTIST + SONG 
	public static final int	SWITCHER_CAT_ALBUM_STRING_RES = R.string.browse_cat_album;
	public static final int	SWITCHER_CAT_ARTIST_STRING_RES = R.string.browse_cat_artists;
	public static final int	SWITCHER_CAT_SONG_STRING_RES = R.string.browse_cat_songs;
	
	/** UI general interaction parameters */
	public static final int CLICK_ACTION_DELAY = 250;
	public static final int CLICK_ANIMATION_DURATION = 300;
	public static final int PLAY_ACTION_DELAY = 750;
	
	/** Album Art Stuff */
	public static final int MIN_ALBUM_ART_SIZE = 100;
	public static final int REASONABLE_ALBUM_ART_SIZE = 256;
	public static final int ALBUM_ART_TEXTURE_SIZE = 256;
	
	/** Search Parameters */
	public static final float SIMILARITY_THRESHOLD = 0.66f;
	
	/** Art Download Parameters */
	public static final int	GET_INET_ART_TOO = 0;
	public static final int	GET_LOCAL_ART_ONLY = 1;
	
	/** Inter Process Variables */
	// album fetching thread - ui
	public static final String	ALBUM_ART_DOWNLOAD_UI_UPDATE_IPC_MSG = "info";
	public static final String	ALBUM_ART_DOWNLOAD_UI_UPDATE_DONE_IPC_MSG = "info_done";
	// art theme processing thread - ui
	public static final String	ALBUM_ART_PROCESSING_UI_UPDATE_IPC_MSG = "info";
	public static final String	ALBUM_ART_PROCESSING_UI_UPDATE_DONE_IPC_MSG = "info_done";
    
	// Main app - ignore sdcard intent
	// FIXME: restart activity not working
//	public static final String	MAIN_ACTIVITY_IGNORE_SD_CARD = "org.abrantix.rockon.rockonnggl.ignoresdcard";
	
	// Service - widget - ui intent ids
//	public static final int MAIN_ACTIVITY_INTENT = R.string.main_activity_intent;
	public static final String PLAYSTATE_CHANGED = "org.abrantix.rockon.rockonnggl.playstatechanged";
    public static final String META_CHANGED = "org.abrantix.rockon.rockonnggl.metachanged";
    public static final String QUEUE_CHANGED = "org.abrantix.rockon.rockonnggl.queuechanged";
    public static final String PLAYBACK_COMPLETE = "org.abrantix.rockon.rockonnggl.playbackcomplete";
    public static final String ASYNC_OPEN_COMPLETE = "org.abrantix.rockon.rockonnggl.asyncopencomplete";
	public static final String PLAYMODE_CHANGED = "org.abrantix.rockon.rockonnggl.playmodechanged";
    
    // The two scrobblers available on the Android Market
    public static final String SCROBBLE_SLS_API = "com.adam.aslfms.notify.playstatechanged";
    public static final String SCROBBLE_SD_API  = "net.jjc1138.android.scrobbler.action.MUSIC_STATUS";
    
    public static final int	SCROBBLE_PLAYSTATE_START = 0;
    public static final int 	SCROBBLE_PLAYSTATE_RESUME = 1;
    public static final int 	SCROBBLE_PLAYSTATE_PAUSE = 2;
    public static final int 	SCROBBLE_PLAYSTATE_COMPLETE = 3;
    

    // mediabutton - service command ids
    public static final String SERVICECMD = "org.abrantix.rockon.rockonnggl.musicservicecommand";
    public static final String CMDNAME = "command";
    public static final String CMDTOGGLEPAUSE = "togglepause";
    public static final String CMDSTOP = "stop";
    public static final String CMDPAUSE = "pause";
    public static final String CMDPREVIOUS = "previous";
    public static final String CMDNEXT = "next";
    
    public static final String CMDSAVE = "save";
    public static final String CMDRESTART = "restart";
    
    public static final String CMDSEEKFWD = "seekfwd";
    public static final String CMDSEEKBACK = "seekback";
    public static final String CMDSEEKAMOUNT = "seekamount";
    
    public static final String TOGGLEPAUSE_ACTION = "org.abrantix.rockon.rockonnggl.musicservicecommand.togglepause";
    public static final String PAUSE_ACTION = "org.abrantix.rockon.rockonnggl.musicservicecommand.pause";
    public static final String PREVIOUS_ACTION = "org.abrantix.rockon.rockonnggl.musicservicecommand.previous";
    public static final String NEXT_ACTION = "org.abrantix.rockon.rockonnggl.musicservicecommand.next";
    public static final String SHUFFLE_NORMAL_ACTION = "org.abrantix.rockon.rockonnggl.musicservicecommand.shufflenormal";
    public static final String SHUFFLE_NONE_ACTION = "org.abrantix.rockon.rockonnggl.musicservicecommand.shufflenone";
    public static final String REPEAT_CURRENT_ACTION = "org.abrantix.rockon.rockonnggl.musicservicecommand.repeatcurrent";
    public static final String REPEAT_NONE_ACTION = "org.abrantix.rockon.rockonnggl.musicservicecommand.repeatnone";
	
    public static final int NOW = 1;
    public static final int NEXT = 2;
    public static final int LAST = 3;

    public static final int START_STICKY = 1;
    
    /* Concert App */
    public static final String CONCERT_APP_PACKAGE = "org.abrantix.rockon.concerts";
    public static final String CONCERT_APP_MAIN_ACTIVITY = "org.abrantix.rockon.concerts.Concerts";
    
    /* Donation Apps */
    public static final String DONATION_APP_PKG_1 = "org.abrantix.rockon.rockonnggl.donate.std.pt"; 
	public static final String DONATION_APP_MAIN_ACTIVITY_1 = "org.abrantix.rockon.rockonnggl.donate.std.pt.Donate";
	public static final String DONATION_APP_PKG_2 = "org.abrantix.rockon.rockonnggl.donate.nicerguy.pt"; 
	public static final String DONATION_APP_MAIN_ACTIVITY_2 = "org.abrantix.rockon.rockonnggl.donate.nicerguy.pt.Donate";
	public static final String DONATION_APP_PKG_3 = "org.abrantix.rockon.rockonnggl.donate.std"; 
	public static final String DONATION_APP_MAIN_ACTIVITY_3 = "org.abrantix.rockon.rockonnggl.donate.std.Donate";
	public static final String DONATION_APP_PKG_4 = "org.abrantix.rockon.rockonnggl.donate.nicerguy"; 
	public static final String DONATION_APP_MAIN_ACTIVITY_4 = "org.abrantix.rockon.rockonnggl.donate.nicerguy.Donate";
    
    /* Releases App */
    public static final String RELEASES_APP_PACKAGE = "org.abrantix.releases";
    public static final String RELEASES_APP_MAIN_ACTIVITY = "org.abrantix.releases.Releases";
    
    /* Widget Stuff */
    public static final String WIDGET_COMPONENT_PACKAGE = "org.abrantix.rockon.rockonnggl";
    public static final String WIDGET_COMPONENT = "org.abrantix.rockon.rockonnggl.RockOnNextGenAppWidgetProvider";
    public static final String WIDGET_COMPONENT_3x3 = "org.abrantix.rockon.rockonnggl.RockOnNextGenAppWidgetProvider3x3";
    public static final String WIDGET_COMPONENT_4x4 = "org.abrantix.rockon.rockonnggl.RockOnNextGenAppWidgetProvider4x4";
    public static final String WIDGET_COMPONENT_4x1 = "org.abrantix.rockon.rockonnggl.RockOnNextGenAppWidgetProvider4x1";
    
    // Activity request codes
    public static final int PREFERENCE_ACTIVITY_REQUEST_CODE = 0;
    public static final int ALBUM_ART_CHOOSER_ACTIVITY_REQUEST_CODE = 1;
    
    /** Progress Update Handler Codes */
    public static final int HARD_REFRESH = 0;
    public static final int KEEP_REFRESHING = 1;
    public static final int DO_NOT_REFRESH = 2;
    
    /** Notification Ids */
    public static final int PLAY_NOTIFICATION_ID = 0;
    
    /** Play modes */
    public static final int SHUFFLE_NONE = 0;
    public static final int SHUFFLE_NORMAL = 1;
    public static final int SHUFFLE_AUTO = 2;
    
    public static final int REPEAT_NONE = 0;
    public static final int REPEAT_CURRENT = 1;
    public static final int REPEAT_ALL = 2;
    
    public static final int FIND_PREV = 0;
    public static final int FIND_NEXT = 1;
    
    /** PLAY QUEUE SIZE WHEN NOT SPECIFICALLY DEFINED */
    public static final int REASONABLE_PLAY_QUEUE_SIZE = 32;
    
	/** File paths */
    private static final String ROCKON_STORAGE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/";
    public static final String ROCKON_BASE_PATH = ROCKON_STORAGE_PATH + "RockOn/";
    public static final String ROCKON_DONATION_PATH = ROCKON_BASE_PATH + "donate";
	public static final String ROCKON_ALBUM_ART_PATH = ROCKON_STORAGE_PATH + "albumthumbs/RockOnNg/";
	public static final String ROCKON_SMALL_ALBUM_ART_PATH = ROCKON_STORAGE_PATH + "albumthumbs/RockOnNg/small/";
	public static final String ROCKON_UNKNOWN_ART_FILENAME = "_____unknown";
    public static final String ROCKON_STORAGE_TYPE_FILENAME = ROCKON_BASE_PATH + "storage_type";
    public static final String ROCKON_EXTERNAL_DIRECTORIES_FILENAME = ROCKON_BASE_PATH + "ext_dirs";
    public static final String ROCKON_INTERNAL_DIRECTORIES_FILENAME = ROCKON_BASE_PATH + "int_dirs";
    
	/** Donation Parameters */
	public static final int	DONATION_INITIAL_INTERVAL = 20;
	public static final int	DONATION_STANDARD_INTERVAL = 30;
	public static final int	DONATION_AFTER_HAVING_DONATED_INTERVAL = 100000;
	
	/** Pontiflex Ad frequency */
	public static final int	PONTIFLEX_INTERVAL = 25;
	
	/** Analytics */
	public static final String	ANALYTICS_MAIN_PAGE = "/RockOnNgGl";
	public static final String	ANALYTICS_MANUAL_ART_PAGE = "/ManualAlbumArt";
	public static final String ANALYTICS_PREFERENCES_PAGE = "/Preferences";
	public static final String ANALYTICS_EQUALIZER_PAGE = "/Equalizer";
	public static final String ANALYTICS_RZ_PROMO = "/RZPromo";
	
	/** Preference keys */ // could be set also in values/preference_strings.xml
	public static final String	prefkey_mBrowseCatMode = "mBrowseCatMode";
	public static final String	prefkey_mRendererMode = "mRendererMode";
	public static final String	prefkey_mTheme = "mTheme";
	public static final String	prefkey_mThemeProcessing = "mThemeProcessing";
	public static final String	prefkey_mThemeBeingProcessed = "mThemeBeingProcessed";
	public static final String	prefkey_mThemeHalfToneDone = "mThemeHalfToneDone";
	public static final String	prefkey_mThemeEarthquakeDone = "mThemeEarthquakeDone";
	public static final String	prefkey_mNavigatorPositionX = "mNavigatorPositionX";
	public static final String prefkey_mNavigatorTargetPositionX = "mNavigatorTargetPositionX";
	public static final String	prefkey_mNavigatorPositionY = "mNavigatorPositionY";
	public static final String prefkey_mNavigatorTargetPositionY = "mNavigatorTargetPositionY";
	public static final String prefkey_mLastAppUiActionTimestamp = "mLastAppUiActionTimestamp"; // everytime the app pauses this is updated
	public static final String prefkey_mLastAppActionTimestamp = "mLastAppActionTimestamp"; // includes also playing song in service
	public static final String prefkey_mPlaylistId = "mPlaylistId";
	public static final String prefkey_mFullscreen = "mFullScreen"; // duplicated in resources... FIXME
	public static final String prefkey_mControlsOnBottom = "mControlsOnBottom"; // duplicated in resources... FIXME
	public static final String prefkey_mAppCreateCount = "mAppCreateCount";
	public static final String prefkey_mAppCreateCountForDonation = "mAppCreateCountForDonation";
	public static final String prefkey_mAppHasDonated = "mAppHasDonated";
	public static final String prefkey_mParent = "parent";
	public static final String prefKey_mEqualizerEnabled = "mEqualizerEnabled";
	
	public static final String prefKey_mEqualizerSettings = "mEqualizerSettings";	

}
