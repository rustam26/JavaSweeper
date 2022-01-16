package sweeper;

class Flag {

    private Matrix flagMap;
    private int countOfClosedBoxes;

    void start() {
        flagMap = new Matrix(Box.CLOSED);

        countOfClosedBoxes = Ranges.getSize().x * Ranges.getSize().y;

    }

    Box get(Coordinate coordinate) {
        return flagMap.get(coordinate);
    }

    void setOpenedToBox(Coordinate coordinate) {

        flagMap.set(coordinate, Box.OPENED);

        countOfClosedBoxes--;
    }

    private void setFlagedToBox(Coordinate coordinate) {

        flagMap.set(coordinate, Box.FLAGED);
    }

    void toggleFlagedToBox(Coordinate coordinate) {
        switch (flagMap.get(coordinate)) {
            case FLAGED:
                setClosedToBox(coordinate);
                break;
            case CLOSED:
                setFlagedToBox(coordinate);
                break;
        }

    }

    private void setClosedToBox(Coordinate coordinate) {

        flagMap.set(coordinate, Box.CLOSED);
    }

    int getCountOfClosedBoxes() {
        return countOfClosedBoxes;

    }

    void setBombedToBox(Coordinate coordinate) {

        flagMap.set(coordinate, Box.BOMBED);
    }

    void setOpenedToClosedBombBox(Coordinate coordinate) {

        if (flagMap.get(coordinate) == Box.CLOSED) {
            flagMap.set(coordinate, Box.OPENED);
        }
    }

    void setNoBombToFlagedSafeBox(Coordinate coordinate) {

        if (flagMap.get(coordinate) == Box.FLAGED) {
            flagMap.set(coordinate, Box.NOBOMB);
        }
    }



     int getCountOfFlagedBoxesAround(Coordinate coordinate) {
        int count = 0;
        for (Coordinate around : Ranges.getCoordinatesAround(coordinate)) {
            if (flagMap.get(around) == Box.FLAGED) {
                count++;
            }
        }
        return count;
    }


}
