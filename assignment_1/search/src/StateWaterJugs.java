public class StateWaterJugs {
    int jugArray[];

    public StateWaterJugs(int[] jugArray) { this.jugArray = jugArray; }

    public StateWaterJugs(StateWaterJugs state) {
        jugArray = new int[3];
        for(int i=0; i<3; i++) {
            this.jugArray[i] = state.jugArray[i];
        }
    }

    public boolean equals(Object o) {
        StateWaterJugs state = (StateWaterJugs) o;

        for (int i=0; i<3; i++)
            if (this.jugArray[i] != state.jugArray[i])
                return false;

        return true;
    }

    public int hashCode() {
        return jugArray[0]*100 + jugArray[1]*10 + jugArray[2];
    }

    public String toString() {
        String ret = "";
        for (int i=0; i<3; i++)
            ret += " " + this.jugArray[i];
        return ret;
    }
}
