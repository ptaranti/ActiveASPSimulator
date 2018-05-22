/*****************************************************************
JADE - Java Agent DEvelopment Framework is a framework to develop
multi-agent systems in compliance with the FIPA specifications.
Copyright (C) 2000 CSELT S.p.A. 

GNU Lesser General Public License

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation, 
version 2.1 of the License. 

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the
Free Software Foundation, Inc., 59 Temple Place - Suite 330,
Boston, MA  02111-1307, USA.
*****************************************************************/


package jade.domain.FIPAAgentManagement;
/** 
* 
* @see jade.domain.FIPAAgentManagement.FIPAManagementOntology
* @author Fabio Bellifemine - CSELT S.p.A.
* @version $Date: 2008/10/10 18:41:49 $ $Revision: 1.1 $
*/

import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;

public class NotUnderstoodException extends FIPAException {

  public NotUnderstoodException(String msg) {
    super(msg);
  }

  public ACLMessage getACLMessage() {
    if (msg == null) {
      msg = new ACLMessage(ACLMessage.NOT_UNDERSTOOD);
      msg.setContent("("+getMessage()+")"); //in SL-0 it must be a t-uple
    } else
      msg.setPerformative(ACLMessage.NOT_UNDERSTOOD);
    return msg;
  }
}
