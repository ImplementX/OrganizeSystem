package com.tjrac.organization.service;


import com.tjrac.organization.pojo.Tag;

import java.util.List;

public interface TagService {
    Tag getTag( int tagId);


    boolean saveTag(Tag newTag);

    boolean removeTag(int tagId);

    boolean updateTag(Tag thisTag);

    List<Tag> listAll();


}
