package com.fjcaballero.gitHubFinder.service.impl;

import com.fjcaballero.gitHubFinder.domain.GitHubBean;
import com.fjcaballero.gitHubFinder.service.ISessionRepository;
import com.fjcaballero.gitHubFinder.util.Util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * The information to be persisted a simple file storage.
 */
@Service
public class MemorySessionService implements ISessionRepository {
	
	private static final Map<String, GitHubBean> sessions = new ConcurrentHashMap<String, GitHubBean>();
	private static final Logger log = Logger.getLogger(MemorySessionService.class);

	private final int hours;

	/**
	 * Initializes a new instance of the FileSessionRepository class.
	 * 
	 * @param Number of hours to considerer a session expired.
	 */
	public MemorySessionService(int hours) {
		this.hours = hours;
	}

	/**
	 * @return Hours when a session is active.
	 */
	public int getHours() {
		return hours;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see gitHubFinderframework.infrastructure.interfaces.ISessionRepository#storeSession(java.lang.String,
	 *      java.util.Map)
	 */
	public boolean storeSession(GitHubBean gitHub) {
		
		if (gitHub != null
				&& gitHub.getId() != null
				&&  gitHub.getId() != "") {
			
			sessions.put(gitHub.getId(), gitHub);
			this.updateExpiredSessions();
			
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * @see gitHubFinderframework.infrastructure.interfaces.ISessionRepository#retrieveSession(java.lang.String)
	 */
	public Map<String, GitHubBean> retrieveSessions () {
		return sessions;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see gitHubFinderframework.infrastructure.interfaces.ISessionRepository#retrieveSession(java.lang.String)
	 */
	public GitHubBean retrieveSession(String id) {
		return sessions.get(id);
	}
	
		/**
	 * {@inheritDoc}
	 * 
	 * @see gitHubFinderframework.infrastructure.interfaces.ISessionRepository#deleteSession(java.lang.String)
	 */
	public boolean deleteSession(String id) {
		sessions.remove(id);

		return true;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see gitHubFinderframework.infrastructure.interfaces.ISessionRepository#resetSessions
	 */
	public synchronized void resetSessions() {
		sessions.clear();

		log.debug(String.format("All sessions were reset."));
	}

	/**
	 * Method to check and update expired sessions.
	 * 
	 * @param sessions
	 */
	private void updateExpiredSessions() {
		if (sessions != null) {
			// Date comparison
			Calendar calendar = new GregorianCalendar();
			calendar.add(Calendar.HOUR, (-1) * this.hours);
			Date now = calendar.getTime();

			List<GitHubBean> expired = new ArrayList<GitHubBean>();

			for (GitHubBean session : sessions.values()) {
				if (session.getStartedAt() == null
						|| session.getStartedAt().before(now)) {
					expired.add(session);
				}
			}

			// Delete
			for (GitHubBean github : expired) {
				sessions.remove(github.getId());

				log.debug(String.format("Session expired: %s.",
						github.toString()));
			}
		}
	}

}
