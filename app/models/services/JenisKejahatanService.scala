package models.services

import javax.inject.Inject

import models.JenisKejahatan
import models.daos.JenisKejahatanDAO
import scala.concurrent.ExecutionContext.Implicits._
import scala.concurrent.Future

class JenisKejahatanService @Inject()(jkDAO: JenisKejahatanDAO) {

  def list: Future[Seq[JenisKejahatan]] = {
    jkDAO.list
  }

  def findByID(id: Int): Future[Option[JenisKejahatan]] = {
    jkDAO.findByID(id)
  }

  def add(jenisK: JenisKejahatan): Future[Option[JenisKejahatan]] = {
    jkDAO.insert(jenisK).map { Int =>
      Option(jenisK.copy(id = Option(Int)))
    }
  }

}
