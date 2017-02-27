package com.atomist.rug.kind.gherkin.rug.kind.gherkin

import com.atomist.rug.kind.gherkin.rug.kind.core.{LazyFileArtifactBackedMutableView, ProjectMutableView}
import com.atomist.rug.kind.gherkin.rug.spi.{ExportFunction, ExportFunctionParameterDescription}
import com.atomist.rug.kind.gherkin.source.FileArtifact

object GherkinMutableView {

  val useful: String = "not really useful"

}

class GherkinMutableView(
                                    originalBackingObject: FileArtifact,
                                    parent: ProjectMutableView,
                                    gherkin: Gherkin
                                  )
  extends LazyFileArtifactBackedMutableView(originalBackingObject, parent) {

  import GherkinMutableView._

  override def nodeName = "Gherkin"

  // Typically you will parse the contents of the original backing object into
  // some useful form
  private val originalContent = originalBackingObject.content
  private var _currentContent = originalContent

  override protected def currentContent: String = _currentContent

  @ExportFunction(readOnly = true, description = "Returns something useful")
  def somethingUseful: String = useful

  @ExportFunction(readOnly = true, description = "Returns something useful")
  def contents: String = currentContent

  @ExportFunction(readOnly = false, description = "Set contents of Gherkin file to `content`")
  def setContents(
                   @ExportFunctionParameterDescription(name = "content", description = "New contents for file") content: String
                 ): Unit = _currentContent = content

}