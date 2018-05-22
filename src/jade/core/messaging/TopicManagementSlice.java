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

package jade.core.messaging;

//#MIDP_EXCLUDE_FILE
//#APIDOC_EXCLUDE_FILE

import jade.core.AID;
import jade.core.IMTPException;
import jade.core.Service;

/**
 * @author Giovanni Caire - Telecom Italia 
 */
public interface TopicManagementSlice extends Service.Slice {
	
	// Horizontal commands
	static final String H_REGISTER = "R";
	static final String H_DEREGISTER = "D";
	
	void register(AID aid, AID topic) throws IMTPException;
	
	void deregister(AID aid, AID topic) throws IMTPException;
}
