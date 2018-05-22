package com.tjrac.organization.service.impl;

import com.tjrac.organization.dao.TagMapper;
import com.tjrac.organization.pojo.Tag;
import com.tjrac.organization.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TagServiceImpl implements TagService{
    @Autowired
    TagMapper tagMapper;
    @Override
    public Tag getTag( int tagId ) {
        return tagMapper.selectByPrimaryKey( tagId );
    }

    @Override
    public boolean saveTag( Tag newTag ) {
        return tagMapper.insert( newTag )>0?true:false;
    }

    @Override
    public boolean removeTag( int tagId ) {
        return tagMapper.deleteByPrimaryKey( tagId )>0?true:false;
    }

    @Override
    public boolean updateTag( Tag thisTag ) {
        return tagMapper.updateByPrimaryKey( thisTag )>0?true:false;
    }

    @Override
    public List< Tag > listAll( ) {
        return tagMapper.selectAll();
    }
}
