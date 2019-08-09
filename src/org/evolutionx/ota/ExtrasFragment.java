package org.evolutionx.ota;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceCategory;
import android.preference.PreferenceFragment;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.widget.ListView;

import org.evolutionx.ota.model.UpdateInfo;

public class ExtrasFragment extends PreferenceFragment implements Preference.OnPreferenceClickListener {

    private static final String EXTRAS_CATEGORY = "extras_category";
    private static final String DEVELOPER_INFO = "developer_info";
    private static final String MAINTAINER_INFO = "maintainer_info";
    private static final String FORUM_INFO = "forum_info";
    private static final String WEBSITE_INFO = "website_info";
    private static final String NEWS_INFO = "news_info";
    private static final String DONATE_INFO = "donate_info";
    private static final String SUPPORT_INFO = "support_info";
    private static String DEVELOPER_URL = "https://github.com/Stallix";
    private static String MAINTAINER_URL = "";
    private static String DONATE_URL = "";
    private static String SUPPORT_URL = "https://paypal.me/joeyhuab";
    private static String FORUM_URL = "";
    private static String WEBSITE_URL = "";
    private static String NEWS_URL = "";
    private PreferenceCategory mExtrasCategory;
    private Preference mDeveloperInfo;
    private Preference mMaintainerInfo;
    private Preference mForumInfo;
    private Preference mWebsiteInfo;
    private Preference mNewsInfo;
    private Preference mDonateInfo;
    private Preference mSupportInfo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.extras_prefs);
        mExtrasCategory = (PreferenceCategory) findPreference(EXTRAS_CATEGORY);
        mDeveloperInfo = findPreference(DEVELOPER_INFO);
        mMaintainerInfo = findPreference(MAINTAINER_INFO);
        mForumInfo = findPreference(FORUM_INFO);
        mWebsiteInfo = findPreference(WEBSITE_INFO);
        mNewsInfo = findPreference(NEWS_INFO);
        mDonateInfo = findPreference(DONATE_INFO);
        mSupportInfo = findPreference(SUPPORT_INFO);
        mDeveloperInfo.setOnPreferenceClickListener(this);
        mMaintainerInfo.setOnPreferenceClickListener(this);
        mForumInfo.setOnPreferenceClickListener(this);
        mWebsiteInfo.setOnPreferenceClickListener(this);
        mNewsInfo.setOnPreferenceClickListener(this);
        mDonateInfo.setOnPreferenceClickListener(this);
        mSupportInfo.setOnPreferenceClickListener(this);
        getPreferenceScreen().removeAll();
    }

    public void updatePrefs(UpdateInfo update) {
        Log.d("ExtrasFragment:updatePrefs", "called");
        if (update == null) {
            Log.d("ExtrasFragment:updatePrefs", "update is null");
            getPreferenceScreen().removeAll();
            return;
        }
        getPreferenceScreen().addPreference(mExtrasCategory);
        mExtrasCategory.removeAll();
        mExtrasCategory.addPreference(mDeveloperInfo);
        mExtrasCategory.addPreference(mSupportInfo);
        if (update.getMaintainer() != null && !update.getMaintainer().isEmpty()) {
            mMaintainerInfo.setSummary(update.getMaintainer());
            mExtrasCategory.addPreference(mMaintainerInfo);
            if (update.getMaintainerUrl() != null && !update.getMaintainerUrl().isEmpty()) {
                MAINTAINER_URL = update.getMaintainerUrl();
            } else {
                MAINTAINER_URL = "";
            }
            mMaintainerInfo.setSelectable(!MAINTAINER_URL.isEmpty());
        }

        if (update.getForumUrl() != null && !update.getForumUrl().isEmpty()) {
            FORUM_URL = update.getForumUrl();
            mExtrasCategory.addPreference(mForumInfo);
        } else {
            FORUM_URL = "";
        }

        if (update.getWebsiteUrl() != null && !update.getWebsiteUrl().isEmpty()) {
            WEBSITE_URL = update.getWebsiteUrl();
            mExtrasCategory.addPreference(mWebsiteInfo);
        } else {
            WEBSITE_URL = "";
        }

        if (update.getNewsUrl() != null && !update.getNewsUrl().isEmpty()) {
            NEWS_URL = update.getNewsUrl();
            mExtrasCategory.addPreference(mNewsInfo);
        } else {
            NEWS_URL = "";
        }

        if (update.getDonateUrl() != null && !update.getDonateUrl().isEmpty()) {
            DONATE_URL = update.getDonateUrl();
            mExtrasCategory.addPreference(mDonateInfo);
        } else {
            DONATE_URL = "";
        }

        if (mExtrasCategory.getPreferenceCount() == 0) {
            getPreferenceScreen().removePreference(mExtrasCategory);
        }
    }

    public void showSnackbar(int stringId, int duration) {
        Snackbar.make(getActivity().findViewById(R.id.main_container), stringId, duration).show();
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        if (preference == mMaintainerInfo) {
            if (MAINTAINER_URL.equals("")) {
                return true;
            }
            try {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(MAINTAINER_URL));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            } catch (Exception ex) {
                showSnackbar(R.string.error_open_url, Snackbar.LENGTH_SHORT);
            }
            return true;
        } else if (preference == mDeveloperInfo) {
            if (DEVELOPER_URL.equals("")) {
                return true;
            }
            try {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(DEVELOPER_URL));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            } catch (Exception ex) {
                showSnackbar(R.string.error_open_url, Snackbar.LENGTH_SHORT);
            }
            return true;
        } else if (preference == mForumInfo) {
            try {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(FORUM_URL));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            } catch (Exception ex) {
                showSnackbar(R.string.error_open_url, Snackbar.LENGTH_SHORT);
            }
            return true;
        } else if (preference == mWebsiteInfo) {
            try {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(WEBSITE_URL));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            } catch (Exception ex) {
                showSnackbar(R.string.error_open_url, Snackbar.LENGTH_SHORT);
            }
            return true;
        } else if (preference == mNewsInfo) {
            try {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(NEWS_URL));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            } catch (Exception ex) {
                showSnackbar(R.string.error_open_url, Snackbar.LENGTH_SHORT);
            }
            return true;
        } else if (preference == mDonateInfo) {
            try {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(DONATE_URL));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            } catch (Exception ex) {
                showSnackbar(R.string.error_open_url, Snackbar.LENGTH_SHORT);
            }
            return true;
        } else if (preference == mSupportInfo) {
            try {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(SUPPORT_URL));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            } catch (Exception ex) {
                showSnackbar(R.string.error_open_url, Snackbar.LENGTH_SHORT);
            }
            return true;
        }
        return false;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ListView list = getView().findViewById(android.R.id.list);
        list.setDivider(null);
    }
}
