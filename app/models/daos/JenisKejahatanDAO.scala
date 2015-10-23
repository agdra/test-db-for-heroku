package models.daos

import javax.inject.Inject

import play.api.db.slick.DatabaseConfigProvider
import slick.lifted.TableQuery

import models.JenisKejahatan
import models.JenisKejahatan.JenisKejahatanT

class JenisKejahatanDAO @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
  extends GenericCRUD[JenisKejahatanT, JenisKejahatan] {
    override val table = TableQuery[JenisKejahatanT]
  }
