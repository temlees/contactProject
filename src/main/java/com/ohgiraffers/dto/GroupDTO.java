package com.ohgiraffers.dto;

public class GroupDTO
{
    private int groupIndex;
    private String groupName;

    public GroupDTO()
    {

    }

    public GroupDTO(int groupIndex, String groupName)
    {
        this.groupIndex = groupIndex;
        this.groupName = groupName;
    }

    public int getGroupIndex()
    {
        return groupIndex;
    }

    public void setGroupIndex(int groupIndex)
    {
        this.groupIndex = groupIndex;
    }

    public String getGroupName()
    {
        return groupName;
    }

    public void setGroupName(String groupName)
    {
        this.groupName = groupName;
    }

    @Override
    public String toString() {
        return "저장되어 있는 그룹 : " +

                "  groupName='" + groupName + '\''                 ;
    }
}
