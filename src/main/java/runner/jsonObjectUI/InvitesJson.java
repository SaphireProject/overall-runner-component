package runner.jsonObjectUI;

import java.util.List;

public class InvitesJson {
    private List<InviteJson> invite;

    public InvitesJson(List<InviteJson> invite) {
        this.invite = invite;
    }

    public List<InviteJson> getInvite() {
        return invite;
    }

    public void setInvite(List<InviteJson> invite) {
        this.invite = invite;
    }
}
