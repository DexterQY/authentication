package per.qy.auth.entity;

import javax.persistence.*;

@Entity
public class SystemRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int parentId;
    @Column(length = 64)
    private String name;
    private int state;
    private int rank;
    private int rank1;
    private int rank2;
    private int rank3;
    private int rank4;
    private int rank5;
    private int rank6;
    private int rank7;
    private int rank8;
    private int rank9;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getRank1() {
        return rank1;
    }

    public void setRank1(int rank1) {
        this.rank1 = rank1;
    }

    public int getRank2() {
        return rank2;
    }

    public void setRank2(int rank2) {
        this.rank2 = rank2;
    }

    public int getRank3() {
        return rank3;
    }

    public void setRank3(int rank3) {
        this.rank3 = rank3;
    }

    public int getRank4() {
        return rank4;
    }

    public void setRank4(int rank4) {
        this.rank4 = rank4;
    }

    public int getRank5() {
        return rank5;
    }

    public void setRank5(int rank5) {
        this.rank5 = rank5;
    }

    public int getRank6() {
        return rank6;
    }

    public void setRank6(int rank6) {
        this.rank6 = rank6;
    }

    public int getRank7() {
        return rank7;
    }

    public void setRank7(int rank7) {
        this.rank7 = rank7;
    }

    public int getRank8() {
        return rank8;
    }

    public void setRank8(int rank8) {
        this.rank8 = rank8;
    }

    public int getRank9() {
        return rank9;
    }

    public void setRank9(int rank9) {
        this.rank9 = rank9;
    }
}
