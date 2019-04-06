package com.sist.hr;

import org.apache.log4j.Logger;

public class MemberDao implements CommonDao {
	Logger log = Logger.getLogger(MemberDao.class);
	
	public void do_save() {
		log.debug("////////////////////");
		log.debug("/do_save////////////");
		log.debug("////////////////////");
	}

	public void do_update() {
		log.debug("////////////////////");
		log.debug("/do_update//////////");
		log.debug("////////////////////");

	}

	public void do_delete() {
		log.debug("////////////////////");
		log.debug("/do_delete//////////");
		log.debug("////////////////////");

	}

	public void do_retrieve() {
		log.debug("////////////////////");
		log.debug("/do_retrieve////////");
		log.debug("////////////////////");

	}

}
