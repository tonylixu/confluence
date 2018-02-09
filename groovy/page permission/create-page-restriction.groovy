import com.atlassian.confluence.event.events.content.page.PageEvent
import com.atlassian.confluence.core.ContentPermissionManager
import com.atlassian.confluence.core.DefaultContentPermissionManager
import com.atlassian.confluence.security.ContentPermission
import com.atlassian.sal.api.component.ComponentLocator
import org.apache.log4j.Logger
import org.apache.log4j.Level

def log = Logger.getLogger("com.onresolve.jira.groovy")
log.setLevel(Level.WARN)
  

def event = event as PageEvent
log.debug("event defined: " + event)

/* For reference only
ContentPermission editRestriction = new ContentPermission()
ContentPermissionManager cpm = new DefaultContentPermissionManager()
Collection<ContentPermission> cp = new ArrayList<ContentPermission>()
editRestriction = ContentPermission.createGroupPermission(ContentPermission.VIEW_PERMISSION,"groupname")
editRestriction.setCreationDate(event.getPage().getCreationDate())           
cp.add(editRestriction)
cpm.setContentPermissions(cp, event.getPage(),ContentPermission.VIEW_PERMISSION);
*/

log.info("Create page set permission script started...")
def permissionManager = ComponentLocator.getComponent(ContentPermissionManager)
def editRestriction = ContentPermission.createGroupPermission(ContentPermission.VIEW_PERMISSION,"group-name")
permissionManager.addContentPermission(editRestriction, event.getPage())