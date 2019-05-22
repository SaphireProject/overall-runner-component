package runner.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import runner.dao.SnapshotsDAO;
import runner.models.Snapshots;

@Service
public class SnapshotService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SnapshotService.class);
    private SnapshotsDAO roomDAO = new SnapshotsDAO();

    public SnapshotService() {
    }

    public void saveSnapshots(Snapshots snapshots) {
        roomDAO.save(snapshots);
    }

    public void deleteSnapshots(Snapshots snapshots) {
        roomDAO.delete(snapshots);
    }

    public void updateSnapshots(Snapshots snapshots) {
        roomDAO.update(snapshots);
    }

    public void findSnapshots(int id) {
        roomDAO.findSnapshotsById(id);
    }

}
