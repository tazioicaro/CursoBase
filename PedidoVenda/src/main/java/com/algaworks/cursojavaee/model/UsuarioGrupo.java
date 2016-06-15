package com.algaworks.cursojavaee.model;





//@Entity
//@Table(name="usuario_grupo")
//public class UsuarioGrupo implements Serializable {

//	private static final long serialVersionUID = 1L;
	
//	
//	   @Id
//	   @GeneratedValue(strategy = GenerationType.IDENTITY)
//	   private Integer id;
//	    
//	    @JoinColumn(name = "grupos_id", referencedColumnName = "id", insertable = true, updatable = true, unique=false)
//	    @OneToOne(optional = false)   
//	    private Grupo grupo;
//	    
//	    @JoinColumn(name = "usuario_id", referencedColumnName = "id", insertable = true, updatable = true, unique=false)
//	    @OneToOne(optional = false)    
//	    private Usuario usuario; 
//
//	    
//	    
//		public Integer getId() {
//			return id;
//		}
//
//		public void setId(Integer id) {
//			this.id = id;
//		}
//
//		public Grupo getGrupo() {
//			return grupo;
//		}
//
//		public void setGrupo(Grupo grupo) {
//			this.grupo = grupo;
//		}
//
//		public Usuario getUsuario() {
//			return usuario;
//		}
//
//		public void setUsuario(Usuario usuario) {
//			this.usuario = usuario;
//		}
//	    
//	    
//
//		@Override
//		public int hashCode() {
//			final int prime = 31;
//			int result = 1;
//			result = prime * result + ((grupo == null) ? 0 : grupo.hashCode());
//			result = prime * result + ((id == null) ? 0 : id.hashCode());
//			result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
//			return result;
//		}
//
//
//
//
//
//		@Override
//		public boolean equals(Object obj) {
//			if (this == obj)
//				return true;
//			if (obj == null)
//				return false;
//			if (getClass() != obj.getClass())
//				return false;
//			UsuarioGrupo other = (UsuarioGrupo) obj;
//			if (grupo == null) {
//				if (other.grupo != null)
//					return false;
//			} else if (!grupo.equals(other.grupo))
//				return false;
//			if (id == null) {
//				if (other.id != null)
//					return false;
//			} else if (!id.equals(other.id))
//				return false;
//			if (usuario == null) {
//				if (other.usuario != null)
//					return false;
//			} else if (!usuario.equals(other.usuario))
//				return false;
//			return true;
//		}
//
//	    
//
//	    

//}
