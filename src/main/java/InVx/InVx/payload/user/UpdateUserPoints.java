package InVx.InVx.payload.user;

import jakarta.validation.constraints.Size;

public class UpdateUserPoints {
    @Size(max = 35)
    private int points;

    @Size(max = 35)
    public int getPoints() {
        return points;
    }

    public void setPoints(@Size(max = 35) int points) {
        this.points = points;
    }
}
