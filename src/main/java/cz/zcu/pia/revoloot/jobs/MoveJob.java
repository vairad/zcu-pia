package cz.zcu.pia.revoloot.jobs;

import cz.zcu.pia.revoloot.manager.IMoveManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MoveJob {

    private static final Logger log = LoggerFactory.getLogger(MoveJob.class);

    final
    IMoveManager moveManager;

    @Autowired
    public MoveJob(IMoveManager moveManager) {
        this.moveManager = moveManager;
    }

    @Scheduled(fixedRate = 5000)
    public void processMoves() {
        moveManager.processMoves(5);
        log.info("All passed moves was processed");
    }
}