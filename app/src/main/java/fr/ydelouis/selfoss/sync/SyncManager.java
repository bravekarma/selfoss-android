package fr.ydelouis.selfoss.sync;

import android.accounts.Account;
import android.content.ContentResolver;
import android.os.Bundle;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import fr.ydelouis.selfoss.account.SelfossAccount;

@EBean
public class SyncManager {

	private static final String AUTHORITY = "fr.ydelouis.selfoss";

	@Bean protected SelfossAccount selfossAccount;

	@AfterInject
	public void setPeriodicSync() {
		Account account = selfossAccount.getAccount();
        if (account != null) {
		    ContentResolver.addPeriodicSync(account, AUTHORITY, new Bundle(), selfossAccount.getSyncPeriod());
        }
	}

	public void requestSync() {
		Account account = selfossAccount.getAccount();
		if (account != null) {
			Bundle extras = new Bundle();
			extras.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL, true);
			extras.putBoolean(ContentResolver.SYNC_EXTRAS_EXPEDITED, true);
			ContentResolver.requestSync(account, AUTHORITY, extras);
		}
	}

	public boolean isActive() {
        Account account = selfossAccount.getAccount();
        if (account != null) {
            return ContentResolver.isSyncActive(selfossAccount.getAccount(), AUTHORITY);
        } else {
            return false;
        }
	}

}
